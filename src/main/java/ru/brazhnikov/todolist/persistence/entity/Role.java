package ru.brazhnikov.todolist.persistence.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

/**
 * Role - класс роли пользователя
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
@Table(name = "roles")
public class Role {

    /**
     * @access private
     * @var int id - перавичный ключ таблицы
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * @access private
     * @var String name - имя роли
     */
    @Column(name = "name")
    private String name;

    /**
     * @access private
     * @var Collection<User> users - связь с пользователями
     */
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    /**
     * @access private
     * @var Collection<Privilege> privileges - связь с привелегиями
     */
    @ManyToMany
    @JoinTable( name = "roles_privileges", joinColumns = @JoinColumn( name = "role_id", referencedColumnName = "id" ),
                inverseJoinColumns = @JoinColumn( name = "privilege_id", referencedColumnName = "id" ) )
    private Collection<Privilege> privileges;

    /**
     * Role - конструктор
     * @param name - название роли
     */
    public Role( String name ) {
        this.name = name;
    }
}
