package ru.brazhnikov.todolist.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.brazhnikov.todolist.persistence.entity.Privilege;

/**
 * PrivilegeRepository - интерфейс сервиса для экземпляров Privilege
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.repositories
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

	/**
	 * findOneByName - найти привелегию по имени
	 * @param name - название привелегии
	 * @return Privilege
	 */
	Privilege findByName(String name);
}
