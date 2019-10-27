package ru.brazhnikov.todolist.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * PersistentLogin - класс сущность
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.entity
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "persistent_logins" )
public class PersistentLogin {

    /**
     * @access private
     * @var int id - первичный ключ таблицы
     */
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    /**
     * @access private
     * @var String username - логин пользователя
     */
    @Column( name = "username", nullable = false )
    private String username;

    /**
     * @access private
     * @var String series -
     */
    @Column( name = "series", nullable = false )
    private String series;

    /**
     * @access private
     * @var String token -
     */
    @Column( name = "token", nullable = false )
    private String token;

    /**
     * @access private
     * @var String lastUsed -
     */
    @Column( name = "last_used", nullable = false )
    private String lastUsed;
}
