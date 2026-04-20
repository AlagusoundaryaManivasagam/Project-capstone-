package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.User;
import com.example.capstone.dto.BudgetDetailDTO;
import com.example.capstone.form.CreateBudgetFormBean;
import com.example.capstone.form.CreateListFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import com.example.capstone.service.implementation.BudgetServiceImpl;
import com.example.capstone.service.implementation.DateServiceImpl;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Month;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Controller
public class BudgetController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @Autowired
    private BudgetDAO budgetDAO;
    @Autowired 
    private DateServiceImpl dateServiceImpl;
    @Autowired
    private BudgetServiceImpl budgetServiceImpl;

    @GetMapping("/budget/budget")
    public ModelAndView budget(CreateListFormBean form){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget");
        User user = authenticatedUserService.loadCurrentUser();
        

        String[] months=dateServiceImpl.giveMonths();
        Calendar calendar = Calendar.getInstance();
        int current= calendar.get(Calendar.MONTH);
        String currentMonth = months[current];
        response.addObject("months",months);
        response.addObject("currentMonth",currentMonth);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        List<Integer> years = dateServiceImpl.giveYears();
        response.addObject("years",years);
        response.addObject("currentYear",currentYear);
        List<Budget> budgets = budgetDAO.getMonthBudgetEntries(user.getId(),current,currentYear);
        response.addObject("budgets", budgets);

        if(form.getYear()!= null && form.getMonth()!= null){
            try {
                int month = Month.valueOf(String.valueOf(form.getMonth()).trim().toUpperCase()).getValue();
                int year = form.getYear();
                List<Budget> monthBudgets = budgetDAO.getMonthBudgetEntries(user.getId(), month, year);
                response.addObject("month", form.getMonth());
                response.addObject("year", year);
                response.addObject("monthBudgets", monthBudgets);
                response.addObject("size", monthBudgets.size());
            }catch (IllegalArgumentException e) {
                // Handle invalid month input
                response.addObject("error", "Invalid month provided");
            } catch (Exception e) {
                // Handle other potential exceptions
                response.addObject("error", "An error occurred while fetching data");
                e.printStackTrace();
            }
        }else {
            //Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            List<Budget> monthBudgets = budgetDAO.getMonthBudgetEntries(user.getId(), month, year);
            response.addObject("monthBudgets", monthBudgets);
            
        }
       


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
            //response.setViewName("budget/budget");
            BudgetDetailDTO model = budgetServiceImpl.createOrUpdateBudget(form,user);
            response.setViewName("budget/budget");
            response.addObject("monthBudgets", model.getBudgets());
            response.addObject("size", model.getSize());
            response.addObject("months", model.getMonths());
            response.addObject("month", model.getMonth());
            response.addObject("year", model.getYear());
            response.addObject("years", model.getYears());
            if (model.getMessage() != null) {
                response.addObject("message", model.getMessage());
            }
        }
        return response;
    }

    @GetMapping("/budget/budget-edit/{budgetId}")
    public ModelAndView budgetEdit(@PathVariable Integer budgetId){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget-edit");
        Budget budget = budgetDAO.findById(budgetId);
        CreateBudgetFormBean form = new CreateBudgetFormBean();
        form.setAmount(budget.getAmount());
        form.setDescription(budget.getDescription());
        form.setId(budget.getId());
        form.setUserId(budget.getUser().getId());
        form.setYear(budget.getYear());
        String[] months=dateServiceImpl.giveMonths();
        form.setMonth(months[budget.getMonth()-1]);
        response.addObject("form", form);
        response.addObject("months",months);
        List<Integer> years = dateServiceImpl.giveYears();
        response.addObject("years",years);
        return response;
    }

    @GetMapping("/budget/budget-delete/{budgetId}")
    public ModelAndView budgetDelete(@PathVariable Integer budgetId){
        ModelAndView response = new ModelAndView();
        response.setViewName("budget/budget");
        Budget budget = budgetDAO.findById(budgetId);
        budgetDAO.delete(budget);
        String message = "Budget deleted";
        response.addObject("message", message);
        User user = authenticatedUserService.loadCurrentUser();
        List<Budget> budgets = budgetDAO.getBudgetEntries(user.getId());
        response.addObject("budgets", budgets);

        response.setViewName("redirect:/budget/budget");
        return response;
    }
}
