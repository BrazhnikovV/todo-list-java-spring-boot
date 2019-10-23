package ru.brazhnikov.todolist.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.brazhnikov.todolist.persistence.entity.Authority;

/**
 * PrivilegeRepository - интерфейс сервиса для экземпляров Privilege
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.repositories
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
public interface AuthorityRepository extends CrudRepository<Authority, Long> {

    /**
     * findByUsername - найти привелегию по имени
     * @param name - название привелегии
     * @return Privilege
     */
    Authority findByUsername( String name );
}
