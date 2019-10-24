package ru.brazhnikov.todolist.service;

import java.util.*;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.utils.AuthorityHelper;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * UserAuthService - класс сервис для авторизации пользователя
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.service
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Service
@Transactional
public class UserAuthService implements UserDetailsService {

    /**
     *  @access private
     *  @var UserRepository userRepository - репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     * UserAuthService - конструктор
     * @param userRepository
     */
    @Autowired
    public UserAuthService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.getUserByUsername( username );

        if ( !optionalUser.isPresent() ) {
            throw new UsernameNotFoundException( "User not found" );
        }

        return new org.springframework.security.core.userdetails.User(
            optionalUser.get().getUsername(),
            optionalUser.get().getPassword(),
            AuthorityHelper.getAuthorities( optionalUser.get().getRoles() )
        );
    }
}
