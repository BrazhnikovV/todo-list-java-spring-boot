package ru.brazhnikov.todolist.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

/**
 * MainController - класс контроллер
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */

@Controller
@Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_USER"})
public class MainController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }
}
