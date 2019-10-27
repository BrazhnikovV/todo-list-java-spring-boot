package ru.brazhnikov.todolist.controllers;

import java.util.ArrayList;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import ru.brazhnikov.todolist.service.UserService;
import org.springframework.validation.BindingResult;
import ru.brazhnikov.todolist.utils.SqlErrorCodeHelper;
import ru.brazhnikov.todolist.representation.UserRepr;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.annotation.Validated;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * @access private
     * @var UserService userService - сервис для работы с пользователем
     */
    private final UserService userService;

    @Autowired
    public RegistryController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/registry")
    public String registryPage( Model model ) {
        model.addAttribute( "user", new UserRepr() );
        return "pages/examples/register";
    }

    @PostMapping("/registry")
    public String registryNewUser(@ModelAttribute("user") @Valid UserRepr userRepr, BindingResult bindingResult ) {
        if ( bindingResult.hasErrors() ) {
            return "pages/examples/register";
        }
        if ( !userRepr.getPassword().equals( userRepr.getMatchingPassword() ) ) {
            bindingResult.rejectValue( "password", "", "Password not matching!" );
            return "pages/examples/register";
        }

        this.userService.register( userRepr );
        return "redirect:/login";
    }

    /**
     * handleError - перехват исключения ConstraintViolationException при аннотации @Column( unique = true ),
     * чтобы отобразить результат валидации в представлении на форме
     * @param ex - объект исключения ConstraintViolationException
     * @param request - объект запроса
     * @param atts - перенапраляемые атрибуты модели или ошибок валидации
     * @return
     */
    @ExceptionHandler( ConstraintViolationException.class )
    public RedirectView handleError( ConstraintViolationException ex, WebRequest request, RedirectAttributes atts ) {

        ArrayList errorMessages = new ArrayList<String>();
        SqlErrorCodeHelper sqlErrorCodeHelper = new SqlErrorCodeHelper();

        String sqlErrorCodeHelperErrorCodes = sqlErrorCodeHelper.getErrorCodes( ex.getSQLException().getSQLState());
        if ( sqlErrorCodeHelperErrorCodes != null ) {
            errorMessages.add(sqlErrorCodeHelperErrorCodes);
        }
        else {
            errorMessages.add(ex.getSQLException().getLocalizedMessage());
        }

        atts.addFlashAttribute("errorMessages", errorMessages);
        RedirectView redirectView = new RedirectView("/registry");

        return redirectView;
    }
}
