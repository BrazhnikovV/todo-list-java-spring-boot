package ru.brazhnikov.todolist.persistence.repositories;

import ru.brazhnikov.todolist.persistence.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * RoleRepository - интерфейс сервиса для экземпляров Role
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.persistence.repositories
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

	/**
	 * findOneByName - найти роль по имени
	 * @param theRoleName - название роли
	 * @return Role
	 */
	Role findOneByName( String theRoleName );

	/**
	 * findOneByName - найти роль по имени
	 * @param roleAdmin - название роли
	 * @return Role
	 */
	Role findByName(String roleAdmin);
}
