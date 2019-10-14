package ru.brazhnikov.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TodoController - класс контроллер
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
public class TodoController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/todo")
    public String todoPage() {
        return "todo";
    }
}
