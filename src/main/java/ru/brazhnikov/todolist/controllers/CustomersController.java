package ru.brazhnikov.todolist.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * CustomersController - класс контроллер
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */

@Controller
@Secured({"ROLE_MANAGER","ROLE_ADMIN","ROLE_USER"})
public class CustomersController {

    @GetMapping("/customers")
    public String indexPage() {
        return "/customers/list";
    }
}
