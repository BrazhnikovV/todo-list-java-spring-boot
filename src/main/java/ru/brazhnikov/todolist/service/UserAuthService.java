package ru.brazhnikov.todolist.service;

import java.util.*;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.persistence.entity.Role;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.security.core.GrantedAuthority;
import ru.brazhnikov.todolist.persistence.entity.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import ru.brazhnikov.todolist.persistence.repositories.RoleRepository;
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

    /**
     *  @access private
     *  @var RoleRepository roleRepository - репозиторий ролей пользователя
     */
    private final RoleRepository roleRepository;

    @Autowired
    public UserAuthService( UserRepository userRepository, RoleRepository roleRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
            getAuthorities( optionalUser.get().getRoles() )
        );
    }

    /**
     * getAuthorities - получить полномочия
     * @param roles - список ролей
     * @return Collection<? extends GrantedAuthority>
     */
    private Collection<? extends GrantedAuthority> getAuthorities( Collection<Role> roles ) {
        return this.getGrantedAuthorities( getPrivileges( roles ) );
    }

    /**
     * getPrivileges - получить привилегии
     * @param roles - список ролей
     * @return List<String>
     */
    private List<String> getPrivileges( Collection<Role> roles ) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for ( Role role : roles ) {
            collection.addAll( role.getPrivileges() );
        }
        for ( Privilege item : collection ) {
            privileges.add( item.getName() );
        }
        return privileges;
    }

    /**
     * getGrantedAuthorities - получить предоставленные полномочия
     * @param privileges - список привилегий
     * @return List<GrantedAuthority>
     */
    private List<GrantedAuthority> getGrantedAuthorities( List<String> privileges ) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for ( String privilege : privileges ) {
            authorities.add( new SimpleGrantedAuthority( privilege ) );
        }
        return authorities;
    }
}
