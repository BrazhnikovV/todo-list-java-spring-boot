package ru.brazhnikov.todolist.representation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotEmpty;

/**
 * UserRepr - класс модель пользователя уровня представления
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.representation
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRepr {

    /**
     * @access private
     * @var Long id - идентификатор задачи
     */
    private Long id;

    /**
     * @access private
     * @var String username - имя пользователя
     */
    @NotEmpty
    private String username;

    /**
     * @access private
     * @var String password - пароль пользователя
     */
    @NotEmpty
    private String password;

    /**
     * @access private
     * @var String matchingPassword - совпадение пароля пользователя
     */
    @NotEmpty
    private String matchingPassword;
}
