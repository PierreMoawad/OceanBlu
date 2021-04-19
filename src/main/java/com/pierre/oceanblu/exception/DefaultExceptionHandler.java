package com.pierre.oceanblu.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Model model, HttpServletRequest req, Exception e) throws Exception {

        log.error(e.getMessage());

        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;

        model.addAttribute("message", e.getMessage());
        model.addAttribute("url", req.getRequestURL());
        return "error";
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UsernameNotFoundException.class)
    public String usernameNotFoundHandler(Exception e) {

        log.error(e.getMessage());
        return "401";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public String pageNotFoundHandler(Exception e) {

        log.error(e.getMessage());
        return "404";
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler({IllegalPurchaseStatusException.class, IllegalRatingFormTypeException.class})
    public String illegalEnumValueExceptionHandler(Exception e) {

        log.error(e.getMessage());
        return "422";
    }
}