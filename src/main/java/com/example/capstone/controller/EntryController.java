package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.dao.BudgetEntryDAO;
import com.example.capstone.database.dao.EntryDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.BudgetEntry;
import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import com.example.capstone.form.CreateEntryFormBean;
import com.example.capstone.form.CreateListFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import service.implementation.DateServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@Slf4j
@Controller
public class EntryController {

    private static final Logger LOG = LoggerFactory.getLogger(EntryController.class);

    @Autowired
    private EntryDAO entryDAO;
    @Autowired
    private BudgetDAO budgetDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @Autowired
    private BudgetEntryDAO budgetEntryDAO;
    @Autowired
    private DateServiceImpl dateServiceImpl;

    @GetMapping("/entries/income")
    public ModelAndView income(CreateListFormBean form){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "i";
        if(form.getYear()!= null && form.getMonth()!= null){
            try {
                int month = Month.valueOf(String.valueOf(form.getMonth()).trim().toUpperCase()).getValue();
                int year = form.getYear();
                List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
                response.addObject("month", form.getMonth());
                response.addObject("year", year);
                response.addObject("incomes", incomes);
                response.addObject("size", incomes.size());
            }catch (IllegalArgumentException e) {
                // Handle invalid month input
                response.addObject("error", "Invalid month provided");
            } catch (Exception e) {
                // Handle other potential exceptions
                response.addObject("error", "An error occurred while fetching data");
                e.printStackTrace();
            }
        }else {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
            response.addObject("incomes", incomes);

        }
       
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

            //response.setViewName("entries/income");
            Entry income = entryDAO.findById(form.getId());
            if(income == null){
             income = new Entry();
            }else{
                String message ="Income edited" ;
                response.addObject("message", message);
            }
            income.setAmount(form.getAmount());
            income.setDate(form.getDate());
            income.setDescription(form.getDescription());
            income.setFlag("i");
            income.setUser(loggedInUser);

            entryDAO.save(income);
            int month= income.getDate().getMonthValue();
            int year = income.getDate().getYear();
            String flag = "i";
            List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
            response.addObject("incomes", incomes);

            String[] months=dateServiceImpl.giveMonths();
            Calendar calendar = Calendar.getInstance();
            int current= month-1;
            String currentMonth = months[current];
            response.addObject("months",months);
            response.addObject("currentMonth",currentMonth);
            int cYear = Calendar.getInstance().get(Calendar.YEAR);
            List<Integer> years = dateServiceImpl.giveYears();
            int currentYear = year;
            response.addObject("years",years);
            response.addObject("currentYear",currentYear);

            response.setViewName("entries/income");
        }

        return response;

    }

    @GetMapping("entries/income-edit/{incomeId}")
    public ModelAndView incomeEdit(@PathVariable Integer incomeId){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income-edit");
        Entry income = entryDAO.findById(incomeId);
        CreateEntryFormBean form = new CreateEntryFormBean();
        form.setAmount(income.getAmount());
        form.setDate(income.getDate());
        form.setDescription(income.getDescription());
        form.setFlag(income.getFlag());
        form.setId(income.getId());
        form.setUserId(income.getUser().getId());
        response.addObject("form", form);
        return response;
    }

    @GetMapping("/entries/income-delete/{incomeId}")
    public ModelAndView deleteIncome(@PathVariable Integer incomeId){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/income");
        Entry income = entryDAO.findById(incomeId);
        int month = income.getDate().getMonthValue();
        int year = income.getDate().getYear();
        log.debug("Year is {} and month is {}",year,month);
        entryDAO.delete(income);
        String message= "Income deleted" ;
        response.addObject("message", message);
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "i";
        List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
        response.addObject("incomes", incomes);
        //response.setViewName("redirect/entries/income");
        return response;


    }

    @GetMapping("/entries/getBudgetsByDate")
    @ResponseBody
    public String getBudgetsByDate(@RequestParam String date) {
        // Call the service to get items based on the date
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

        int m = localDate.getMonthValue();
        int y = localDate.getYear();
        List<Budget> items = budgetDAO.getMonthBudgetEntries(loggedInUser.getId(), m, y);
        StringBuilder html = new StringBuilder();
        
        
        for (Budget item : items) {
            html.append("<option value=\"")
                .append(item.getId())
                .append("\">")
                .append(item.getDescription())
                .append("</option>");
        }
        
        
        return html.toString();
    }

    @GetMapping("/entries/expense")
    public ModelAndView expense (CreateListFormBean form, BindingResult bindingResult) throws IOException {
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense");
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "e";
        if(form.getYear()!= null && form.getMonth()!= null){
            int month= Month.valueOf(String.valueOf(form.getMonth()).trim().toUpperCase()).getValue();
            int year = form.getYear();
            List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
            response.addObject("month", form.getMonth());
            response.addObject("year", year);
            response.addObject("expenses", expenses);
            response.addObject("size",expenses.size());

        }else {
            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
            response.addObject("expenses", expenses);
        }

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        int year = Calendar.getInstance().get(Calendar.YEAR);

        List<Budget> budgets = budgetDAO.getMonthBudgetEntries(loggedInUser.getId(),month, year);
            response.addObject("budgets", budgets);



        String[] months=dateServiceImpl.giveMonths();
        int current= calendar.get(Calendar.MONTH);
        String currentMonth = months[current];
        response.addObject("months",months);
        response.addObject("currentMonth",currentMonth);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<Integer> years = dateServiceImpl.giveYears();
        response.addObject("years",years);
        response.addObject("currentYear",currentYear);

        return response;
    }

    @PostMapping("/entries/expense-create")
    public ModelAndView expenseSubmit(@Valid CreateEntryFormBean form, BindingResult bindingResult){
        ModelAndView response= new ModelAndView();
        User loggedInUser = authenticatedUserService.loadCurrentUser();

        List<Budget> budgets = budgetDAO.getBudgetEntries(loggedInUser.getId());
        if(budgets.isEmpty()){
            bindingResult.rejectValue("budgetCategory","BudgetCategoryCode", "Please create budgets before creating expenses");

        }else if(form.getBudgetCategory()==null){
            bindingResult.rejectValue("budgetCategory","BudgetCategoryCode", "Please choose one budget Category");

        }

        if ( bindingResult.hasErrors() ) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                LOG.debug(error.toString());
            }

            response.setViewName("entries/expense");
            response.addObject("bindingResult", bindingResult);
            response.addObject("form", form);

        }else {
            response.setViewName("entries/expense");
            Entry expense = entryDAO.findById(form.getId());
            Budget budget= new Budget();
            if(expense==null){
             expense = new Entry();
             budget = budgetDAO.findById(form.getBudgetCategory());


            }else{
                budget = budgetDAO.findById(form.getBudgetCategory());
                String message = "Expense edited";
                response.addObject("message", message);
            }
            expense.setAmount(form.getAmount());
            expense.setDate(form.getDate());
            expense.setDescription(form.getDescription());
            expense.setFlag("e");
            expense.setUser(loggedInUser);

            entryDAO.save(expense);
            BudgetEntry budgetEntry= new BudgetEntry();

            if(form.getId() == null){
            budgetEntry.setEntry(expense);
            budgetEntry.setBudget(budget);
            budgetEntry.setDate(form.getDate());
            budgetEntryDAO.save(budgetEntry);
            }
            else{
                budgetEntry= budgetEntryDAO.getByExpenseId(expense.getId());
                budgetEntry.setBudget(budget);
                budgetEntry.setDate(form.getDate());
                budgetEntryDAO.save(budgetEntry);
            }
            int month = expense.getDate().getMonthValue();
            int year = expense.getDate().getYear();
            String flag = "e";
            List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
            response.addObject("expenses",expenses);
            //response.setViewName("redirect:/entries/expense");
        }
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

        return response;
    }

    @GetMapping("/entries/expense-edit/{expenseId}")
    public ModelAndView expenseEdit(@PathVariable Integer expenseId){
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense-edit");
        Entry expense= entryDAO.findById(expenseId);
        CreateEntryFormBean form = new CreateEntryFormBean();
        form.setAmount(expense.getAmount());
        form.setDate(expense.getDate());
        form.setDescription(expense.getDescription());
        form.setFlag(expense.getFlag());
        form.setUserId(expense.getUser().getId());
        form.setId(expense.getId());
        BudgetEntry budgetEntry = budgetEntryDAO.getByExpenseId(expense.getId());
        Integer budgetCategory = budgetEntry.getBudgetId();
        form.setBudgetCategory(budgetCategory);
        List<Budget> budgets = budgetDAO.getBudgetEntries(loggedInUser.getId());
        response.addObject("budgets", budgets);
        response.addObject("budgetCategory", budgetCategory);

        response.addObject("form", form);
        return response;
    }

    @GetMapping("/entries/expense-delete/{expenseId}")
    public ModelAndView expenseDelete(@PathVariable Integer expenseId){
        ModelAndView response= new ModelAndView();
        response.setViewName("entries/expense");
        Entry expense= entryDAO.findById(expenseId);
        int month = expense.getDate().getMonthValue();
        int year = expense.getDate().getYear();
        entryDAO.delete(expense);
        String message = "Expense deleted";
        response.addObject("message", message);
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag = "e";
        List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
        response.addObject("expenses", expenses);

        //response.setViewName("redirect:/entries/expense");
        return response;

    }
    
    
}
