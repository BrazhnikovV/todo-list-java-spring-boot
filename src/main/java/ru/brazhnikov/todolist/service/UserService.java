package ru.brazhnikov.todolist.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.brazhnikov.todolist.persistence.entity.User;
import ru.brazhnikov.todolist.persistence.repositories.UserRepository;
import ru.brazhnikov.todolist.representation.UserRepr;

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

    @Autowired
    public UserService( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    /**
     * create - создать пользователя
     * @param userRepr
     * @return void
     */
    public void create( UserRepr userRepr ) {
        User user = new User();
        user.setUsername( userRepr.getUsername() );
        user.setPassword( userRepr.getPassword() );
        this.userRepository.save( user );
    }
}
