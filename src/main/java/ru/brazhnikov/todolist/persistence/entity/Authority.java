package ru.brazhnikov.todolist.persistence.entity;

import lombok.Setter;
import lombok.Getter;
import javax.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Privilege - класс привелегий роли
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.entity
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Getter
@Setter
@Entity(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    /**
     * @access private
     * @var int id - перавичный ключ таблицы
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * @access private
     * @var String username - название пользователя
     */
    @Column( unique = true, nullable = false )
    private String username;

    /**
     * @access private
     * @var String username - название пользователя
     */
    @Column( nullable = false )
    private String authority;

    /**
     * Authority - конструктор для создании записи об авторизованном пользователе
     * @param name - имя пользователя
     * @param role - роль пользователя
     */
    public Authority( String name, String role ) {
        this.username = name;
        this.authority = role;
    }
}
