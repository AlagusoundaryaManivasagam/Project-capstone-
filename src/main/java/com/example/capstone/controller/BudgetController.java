package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.User;
import com.example.capstone.form.CreateBudgetFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class BudgetController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @Autowired
    private BudgetDAO budgetDAO;

    @GetMapping("/budget/budget")
    public ModelAndView budget(){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget");
        User user = authenticatedUserService.loadCurrentUser();
        List<Budget> budgets = budgetDAO.getBudgetEntries(user.getId());
        response.addObject("budgets", budgets);
        return response;
    }

    @PostMapping("/budget/budget-create")
    public ModelAndView budgetCreate(@Valid CreateBudgetFormBean form, BindingResult bindingResult){
        ModelAndView response = new ModelAndView();
        User user = authenticatedUserService.loadCurrentUser();

        if ( bindingResult.hasErrors() ) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                LOG.debug(error.toString());
            }

            response.setViewName("budget/budget");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        }else {
            Budget budget = new Budget();
            budget.setAmount(form.getAmount());
            budget.setDescription(form.getDescription());
            budget.setUser(user);

            budgetDAO.save(budget);
            response.setViewName("redirect:/budget/budget");
        }
        return response;
    }
}
