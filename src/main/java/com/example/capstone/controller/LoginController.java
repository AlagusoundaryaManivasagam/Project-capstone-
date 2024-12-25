package com.example.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/login/signUp")
    public ModelAndView signUp(){
        ModelAndView response = new ModelAndView();
        response.setViewName("login/signUp");
        return response;
    }

    @GetMapping("/login/login")
    public ModelAndView login(){
        ModelAndView response = new ModelAndView();
        response.setViewName("login/login");
        return response;
    }
}
