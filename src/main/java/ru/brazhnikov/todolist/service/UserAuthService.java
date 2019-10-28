package ru.brazhnikov.todolist.service;

import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.persistence.entity.Role;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        User user = this.userRepository.findByUsername( username );

        if ( user == null ) {
            throw new UsernameNotFoundException( "User not found" );
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
            true, true, true, true,
            this.getAuthorities( user.getRoles() )
        );
    }

    /**
     * getAuthorities - получить полномочия
     * @param roles - роли пользователя
     * @return Collection<? extends GrantedAuthority>
     */
    private final Collection<? extends GrantedAuthority> getAuthorities( Collection<Role> roles ) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for ( Role role: roles ) {
            authorities.add( new SimpleGrantedAuthority( role.getName() ) );
            authorities.addAll( role.getPrivileges()
                    .stream()
                    .map( p -> new SimpleGrantedAuthority( p.getName() ) )
                    .collect( Collectors.toList() ) );
        }
        return authorities;
    }
}
