package ru.brazhnikov.todolist.persistence.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * User - класс сущность пользователя
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
     * @var String username - логин пользователя
     */
    @NotEmpty
    @Column( name = "username", unique = true, nullable = false )
    private String username;

    /**
     * @access private
     * @var String firstName - имя пользователя
     */
    //@Column( nullable = false )
    private String firstName;

    /**
     * @access private
     * @var String lastName - фамилия пользователя
     */
    //@Column( nullable = false )
    private String lastName;

    /**
     * @access private
     * @var String email - почтовый ящик пользователя
     */
    //@Column( nullable = false )
    private String email;

    /**
     * @access private
     * @var boolean enabled - отключенный пользователь
     */
    //@Column( nullable = false )
    private boolean enabled;

    /**
     * @access private
     * @var boolean tokenExpired - токен истекший
     */
    //@Column( nullable = false )
    private boolean tokenExpired;

    /**
     * @access private
     * @var String password - пароль пользователя
     */
    @NotEmpty
    @Column( name = "password", nullable = false )
    private String password;

    /**
     * @access private
     * @var Collection<Role> roles - связь с ролями
     */
    @ManyToMany
    @JoinTable( name = "users_roles",
                joinColumns = @JoinColumn( name = "user_id" ),
                inverseJoinColumns = @JoinColumn( name = "role_id" ) )
    private Collection<Role> roles;
}
