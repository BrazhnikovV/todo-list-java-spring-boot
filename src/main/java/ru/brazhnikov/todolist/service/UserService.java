package ru.brazhnikov.todolist.service;

import lombok.Setter;
import java.util.Arrays;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.representation.UserRepr;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import ru.brazhnikov.todolist.persistence.repositories.RoleRepository;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
     *  @var BCryptPasswordEncoder passwordEncoder - кодировщик пароля(должен совпадать
     *  с бином объявленном в классе TodoListApplication)
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * constructor - конструктор
     * @param userRepository - репозиторий пользователь
     * @param passwordEncoder - кодировщик паролей
     * @param roleRepository - роль пользователя
     */
    @Autowired
    public UserService( UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * create - создать пользователя
     * @param userRepr
     * @return void
     */
    public void create( UserRepr userRepr ) {
        User user = new User();
        user.setUsername( userRepr.getUsername() );
        user.setPassword( this.passwordEncoder.encode( userRepr.getPassword() ) );
        user.setRoles( Arrays.asList( this.roleRepository.findOneByName("ROLE_USER") ) );
        this.userRepository.save( user );
    }
}
