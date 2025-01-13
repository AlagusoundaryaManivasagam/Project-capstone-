package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.dao.EntryDAO;
import com.example.capstone.database.dao.UserDAO;
import com.example.capstone.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class SummaryController {
    private static final Logger LOG = LoggerFactory.getLogger(SummaryController.class);

    @Autowired
    private EntryDAO entryDAO;
    @Autowired
    private BudgetDAO budgetDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/summary/summary")
    public ModelAndView summary() {
        ModelAndView response = new ModelAndView();
        response.setViewName("summary/summary");
        return response;
    }
}
