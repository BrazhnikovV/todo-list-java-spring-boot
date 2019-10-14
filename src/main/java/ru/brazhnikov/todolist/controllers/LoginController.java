package ru.brazhnikov.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController - класс контроллер для аутентификации пользователя
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
