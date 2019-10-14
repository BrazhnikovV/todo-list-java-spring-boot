package ru.brazhnikov.todolist.service;

import java.util.Optional;
import java.util.Collections;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;

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

    @Autowired
    public UserAuthService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    /**
     * loadUserByUsername
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Optional<User> optionalUser = this.userRepository.getUserByUsername( username );

        if ( !optionalUser.isPresent() ) {
            throw new UsernameNotFoundException( "User not found" );
        }

        return new org.springframework.security.core.userdetails.User(
            optionalUser.get().getUsername(),
            optionalUser.get().getPassword(),
            Collections.singletonList( new SimpleGrantedAuthority( "USER" ))
        );
    }
}
