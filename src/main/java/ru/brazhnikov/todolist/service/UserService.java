package ru.brazhnikov.todolist.service;

import lombok.Setter;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.representation.UserRepr;
import ru.brazhnikov.todolist.persistence.entity.User;
import ru.brazhnikov.todolist.persistence.entity.Role;
import ru.brazhnikov.todolist.persistence.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import ru.brazhnikov.todolist.persistence.repositories.RoleRepository;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.brazhnikov.todolist.persistence.repositories.AuthorityRepository;

/**
 * AccountService - класс сервиса для работы с данными об акаунтах
 *
 * @version 1.0.1
 * @package ru.brazhnikov.services
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Setter
@Service
public class UserService {

    /**
     *  @access private
     *  @var UserRepository userRepository - репозиторий пользователя
     */
    private final UserRepository userRepository;

    /**
     *  @access private
     *  @var RoleRepository roleRepository - репозиторий роли пользователя
     */
    private RoleRepository roleRepository;

    /**
     *  @access private
     *  @var String registryDefaultRole -
     */
    private String registryDefaultRole = "ROLE_USER";

    /**
     *  @access private
     *  @var BCryptPasswordEncoder passwordEncoder - кодировщик пароля(должен совпадать
     *  с бином объявленном в классе TodoListApplication)
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     *  @access private
     *  @var AuthorityRepository authorityRepository -
     */
    private AuthorityRepository authorityRepository;

    /**
     * constructor - конструктор
     * @param userRepository - репозиторий пользователь
     * @param passwordEncoder - кодировщик паролей
     * @param roleRepository - роль пользователя
     */
    @Autowired
    public UserService(
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            RoleRepository roleRepository,
            AuthorityRepository authorityRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    /**
     * register - зарегистрировать пользователя
     * @param userRepr
     * @return void
     */
    public void register( UserRepr userRepr ) {

        User user = new User();
        Collection<Role> roles = Arrays.asList( this.roleRepository.findOneByName( this.registryDefaultRole ) );
        user.setUsername( userRepr.getUsername() );
        user.setPassword( this.passwordEncoder.encode( userRepr.getPassword() ) );
        user.setEnabled( true );
        user.setRoles( roles );

        Authority authority = this.authorityRepository.findByUsername( userRepr.getUsername() );
        if ( authority == null ) {
            authority = new Authority( user.getUsername(), this.registryDefaultRole );
            this.authorityRepository.save( authority );
        }
        this.userRepository.save( user );
    }
}
