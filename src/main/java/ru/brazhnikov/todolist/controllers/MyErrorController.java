package ru.brazhnikov.todolist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

/**
 * MyErrorController - класс контроллер для обработки 404-й ошибки
 * (стилизация стандартной спринговой страницы)
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.controllers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Controller
class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "/pages/errors/error404";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
