package ru.brazhnikov.todolist.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HelpController - класс контроллер
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
@RequestMapping("/help")
@Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_USER"})
public class HelpController {

    @GetMapping
    public String indexPage() {
        return "help";
    }
}
