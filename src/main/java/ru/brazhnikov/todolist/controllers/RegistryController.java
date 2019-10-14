package ru.brazhnikov.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * RegistryController - класс контроллер для регистрации пользователя
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
public class RegistryController {

    @GetMapping("/registry")
    public String registryPage() {
        return "registry";
    }
}
