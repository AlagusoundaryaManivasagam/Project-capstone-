package com.example.capstone.controller;

import com.example.capstone.database.entity.Entry;
import com.example.capstone.form.CreateEntryFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class EntryController {

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/entries/income")
    public ModelAndView income(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        return response;
    }

    @GetMapping("entries/income/create")
    public ModelAndView createIncomeSubmit(@Valid CreateEntryFormBean form, BindingResult bindingResult){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        Entry income= new Entry();
        income.setAmount(form.getAmount());
        income.setDate(form.getDate());
        income.setDescription(form.getDescription());
        income.setFlag("i");


        return response;

    }


    @GetMapping("/entries/expense")
    public ModelAndView expense(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense");
        return response;
    }

}
