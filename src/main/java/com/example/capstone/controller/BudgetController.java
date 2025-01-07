package com.example.capstone.controller;

import com.example.capstone.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class BudgetController {

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/budget/budget")
    public ModelAndView budget(){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget");
        return response;
    }
}
