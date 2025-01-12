package com.example.capstone.controller;

import com.example.capstone.database.dao.EntryDAO;
import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import com.example.capstone.form.CreateEntryFormBean;
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

import static com.mysql.cj.conf.PropertyKey.logger;

@Slf4j
@Controller
public class EntryController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private EntryDAO entryDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/entries/income")
    public ModelAndView income(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "i";
        List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(), flag);
        response.addObject("incomes", incomes);
        return response;
    }

    @PostMapping("/entries/income-create")
    public ModelAndView createIncomeSubmit(@Valid CreateEntryFormBean form, BindingResult bindingResult){

        ModelAndView response= new ModelAndView();
        User loggedInUser = authenticatedUserService.loadCurrentUser();

        if ( bindingResult.hasErrors() ) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                LOG.debug(error.toString());
            }

            response.setViewName("entries/income");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        }else {


            Entry income = new Entry();
            income.setAmount(form.getAmount());
            income.setDate(form.getDate());
            income.setDescription(form.getDescription());
            income.setFlag("i");
            income.setUser(loggedInUser);



            entryDAO.save(income);
            response.setViewName("redirect:/entries/income");
        }

        return response;

    }


    @GetMapping("/entries/expense")
    public ModelAndView expense(){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense");
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "e";
        List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(), flag);
        response.addObject("expenses", expenses);
        return response;
    }

    @PostMapping("/entries/expense-create")
    public ModelAndView expenseSubmit(@Valid CreateEntryFormBean form, BindingResult bindingResult){
        ModelAndView response= new ModelAndView();
        User loggedInUser = authenticatedUserService.loadCurrentUser();

        if ( bindingResult.hasErrors() ) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                LOG.debug(error.toString());
            }

            response.setViewName("entries/expense");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        }else {
            response.setViewName("entries/expense");
            Entry expense = new Entry();
            expense.setAmount(form.getAmount());
            expense.setDate(form.getDate());
            expense.setDescription(form.getDescription());
            expense.setFlag("e");
            expense.setUser(loggedInUser);

            entryDAO.save(expense);
            response.setViewName("redirect:/entries/expense");
        }
        return response;
    }
}
