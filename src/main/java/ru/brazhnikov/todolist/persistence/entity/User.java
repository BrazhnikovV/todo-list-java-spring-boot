package ru.brazhnikov.todolist.persistence.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * User - класс сущность пользователя
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Data
@Entity
@Table(name = "users")
public class User {

    /**
     * @access private
     * @var int id - перавичный ключ таблицы
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    /**
     * @access private
     * @var String username - имя пользователя
     */
    @Column( unique = true, nullable = false )
    private String username;

    /**
     * @access private
     * @var String password - пароль пользователя
     */
    @Column( nullable = false )
    private String password;
}
