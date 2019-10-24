package ru.brazhnikov.todolist.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TodoController - класс контроллер
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
@RequestMapping("/todo")
@Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_USER"})
public class TodoController {

    @GetMapping
    public String indexPage() {
        return "todo";
    }
}
