package com.example.capstone.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@Controller
@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NoResourceFoundException.class)
    @RequestMapping(value = {"/error/404", "/404"})
    public ModelAndView error404(HttpServletRequest request) {
        // This is used in the security config for 404 pages
        ModelAndView response = new ModelAndView("error/404");
        response.setStatus(HttpStatus.NOT_FOUND);

        log.debug("404 error for " + request.getRequestURI());

        return response;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(value = {"/error/500", "/500"})
    public ModelAndView error500(HttpServletRequest request, Exception ex) {
        ModelAndView response = new ModelAndView("error/500");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        log.debug("500 error for " + request.getRequestURI(), ex);
        response.addObject("exceptionMessage", ex.getMessage());


        return response;
    }
}
