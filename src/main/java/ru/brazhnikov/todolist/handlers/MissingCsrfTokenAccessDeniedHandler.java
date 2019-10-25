package ru.brazhnikov.todolist.handlers;

import lombok.Getter;
import lombok.Setter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

/**
 * MissingCsrfTokenAccessDeniedHandler - класс обработчик потери (отсутствия) CsrfToken
 *
 * @version 1.0.1
 * @package ru.brazhnikov.todolist.handlers
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2019, Vasya Brazhnikov
 */
@Getter
@Setter
public class MissingCsrfTokenAccessDeniedHandler extends AccessDeniedHandlerImpl {

    /**
     * @access private
     * @var RequestCache requestCache -
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * @access private
     * @var String loginPage - url адрес редиректа на страница авторизации(аутентификации)
     */
    private String loginPage = "/login";

    @Override
    public void handle( HttpServletRequest req,
                        HttpServletResponse res,
                        AccessDeniedException exception ) throws IOException, ServletException {

        if (exception instanceof MissingCsrfTokenException && isSessionInvalid(req)) {
            requestCache.saveRequest(req, res);
            res.sendRedirect(req.getContextPath() + loginPage);
        }
        super.handle( req, res, exception );
    }

    /**
     * isSessionInvalid - валидная ли сессия
     * @param req - объект запроса
     * @return boolean
     */
    private boolean isSessionInvalid( HttpServletRequest req ) {
        try {
            HttpSession session = req.getSession(false );
            return session == null || !req.isRequestedSessionIdValid();
        }
        catch ( IllegalStateException ex ) {
            return true;
        }
    }
}
