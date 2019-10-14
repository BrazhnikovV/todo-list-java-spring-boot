package ru.brazhnikov.todolist.representation;

import lombok.Data;
import java.time.LocalDate;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TodoRepr - класс модель задачи уровня представления
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.representation
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRepr {

    /**
     * @access private
     * @var Long id - идентификатор задачи
     */
    private Long id;

    /**
     * @access private
     * @var String description - описание задачи
     */
    @NotEmpty
    private String description;

    /**
     * @access private
     * @var String username - имя пользователя
     */
    @NotEmpty
    private String username;

    /**
     * @access private
     * @var LocalDate targetDate - время создания задачи
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;
}
