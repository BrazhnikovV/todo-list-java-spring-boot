package ru.brazhnikov.todolist.persistence.repositories;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository - интерфейс сервиса для экземпляров User
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.repositories
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * existsByUsername - определить существование пользователя по имени
     * @param username
     * @return
     */
    boolean existsByUsername( String username );

    /**
     * getUserByUsername - получить пользователя по имени
     * @param username
     * @return
     */
    Optional<User> getUserByUsername( String username );
}
