package ru.brazhnikov.todolist.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import ru.brazhnikov.todolist.representation.UserRepr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.brazhnikov.todolist.service.UserService;

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

    private final UserService userService;

    @Autowired
    public RegistryController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/registry")
    public String registryPage( Model model ) {
        model.addAttribute( "user", new UserRepr() );
        return "registry";
    }

    @PostMapping("/registry")
    public String registryNewUser(@ModelAttribute("user") @Valid UserRepr userRepr, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return "registry";
        }
        if ( !userRepr.getPassword().equals( userRepr.getMatchingPassword() ) ) {
            bindingResult.rejectValue( "password", "", "Password not matching!" );
            return "registry";
        }

        this.userService.create( userRepr );
        return "redirect:/login";
    }
}
