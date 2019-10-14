package ru.brazhnikov.todolist.persistence.repositories;

import org.springframework.stereotype.Repository;
import ru.brazhnikov.todolist.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
