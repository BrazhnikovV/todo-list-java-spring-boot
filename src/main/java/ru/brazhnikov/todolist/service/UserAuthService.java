package ru.brazhnikov.todolist.service;

import java.util.*;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.persistence.entity.Authority;
import ru.brazhnikov.todolist.persistence.entity.Role;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import ru.brazhnikov.todolist.persistence.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.brazhnikov.todolist.persistence.repositories.AuthorityRepository;
import ru.brazhnikov.todolist.persistence.repositories.RoleRepository;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.brazhnikov.todolist.utils.AuthorityHelper;

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

    /**
     *  @access private
     *  @var RoleRepository roleRepository - репозиторий ролей пользователя
     */
    private final RoleRepository roleRepository;

    /**
     *  @access private
     *  @var RoleRepository roleRepository - репозиторий ролей пользователя
     */
    private final AuthorityRepository authorityRepository;

    /**
     * UserAuthService - конструктор
     * @param userRepository
     * @param roleRepository
     * @param authorityRepository
     */
    @Autowired
    public UserAuthService( UserRepository userRepository, RoleRepository roleRepository, AuthorityRepository authorityRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
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
