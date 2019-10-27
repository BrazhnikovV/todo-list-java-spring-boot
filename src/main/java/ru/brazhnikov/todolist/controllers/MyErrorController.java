package ru.brazhnikov.todolist.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.web.servlet.error.ErrorController;

/**
 * MyErrorController - класс контроллер для обработки ошибок
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
    @ResponseBody
    public ModelAndView handleError(HttpServletRequest request) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if ((authentication instanceof AnonymousAuthenticationToken)) {
//            return "redirect:/login";
//        }

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        switch ( statusCode ) {
            case (403):
                return new ModelAndView("/pages/errors/error403");
            case (404):
                return new ModelAndView("/pages/errors/error404");
            case (405):
                return new ModelAndView("/pages/errors/error405");
            case (500):
                return new ModelAndView("/pages/errors/error500");
            default:
                return new ModelAndView("/pages/errors/errorUnknown");
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
