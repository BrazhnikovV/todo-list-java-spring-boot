package ru.brazhnikov.todolist.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Privilege {

    /**
     * @access private
     * @var int id - перавичный ключ таблицы
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * @access private
     * @var String name - название привилегии
     */
    @Column( unique = true, nullable = false )
    private String name;

    /**
     * @access private
     * @var Collection<Role> roles - связь с ролями
     */
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    /**
     * Privilege - конструктор для создании записи о привилегиях пользователя
     * @param name - названии привилегии
     */
    public Privilege( String name ) {
        this.name = name;
    }
}
