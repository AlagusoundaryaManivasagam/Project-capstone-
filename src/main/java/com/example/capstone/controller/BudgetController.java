package com.example.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class BudgetController {

    @GetMapping("/budget/budget")
    public ModelAndView budget(){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget");
        return response;
    }
}
