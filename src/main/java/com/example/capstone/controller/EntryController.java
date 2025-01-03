package com.example.capstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class EntryController {
    @GetMapping("/entries/income")
    public ModelAndView income(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        return response;
    }

    @GetMapping("/entries/expense")
    public ModelAndView expense(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense");
        return response;
    }

}
