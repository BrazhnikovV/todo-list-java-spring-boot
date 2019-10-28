package ru.brazhnikov.todolist.persistence.repositories;

import org.springframework.stereotype.Repository;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository - интерфейс сервиса для экземпляров User
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.repositories
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * findByUsername - получить пользователя по имени
     * @param username
     * @return
     */
    User findByUsername(String username);
}
