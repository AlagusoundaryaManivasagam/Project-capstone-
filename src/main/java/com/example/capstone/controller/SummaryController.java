package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.dao.BudgetEntryDAO;
import com.example.capstone.database.dao.EntryDAO;
import com.example.capstone.database.dao.UserDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.BudgetEntry;
import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import com.example.capstone.dto.SummaryDTO;
import com.example.capstone.form.CreateEntryFormBean;
import com.example.capstone.form.CreateListFormBean;
import com.example.capstone.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import service.implementation.DateServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Controller
public class SummaryController {
    private static final Logger LOG = LoggerFactory.getLogger(SummaryController.class);

    @Autowired
    private BudgetEntryDAO budgetEntryDAO;
    @Autowired
    private EntryDAO entryDAO;
    @Autowired
    private BudgetDAO budgetDAO;
    @Autowired
    private AuthenticatedUserService authenticatedUserService;
    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;
    @Autowired
    private DateServiceImpl dateServiceImpl;


    @GetMapping("/summary/summary")
    public ModelAndView summary(@RequestParam(required = false) String a,
                                @RequestParam(required = false) Integer b,
                                CreateListFormBean form) {

        ModelAndView response = new ModelAndView();


        if(a!= null && b!=null) {

            String message = "Status of "+ a+","+b;
            response.addObject("message", message);

        }
        response.setViewName("summary/summary");
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

        User loggedInUser = authenticatedUserService.loadCurrentUser();
        String flag= "i";
        List<Entry> incomes = new ArrayList<>();
        int month= 0;
        int year = 0;
        if(form.getMonth()!= null && form.getYear()!=null) {
            month=Month.valueOf(String.valueOf(form.getMonth()).trim().toUpperCase()).getValue();
            year = form.getYear();
            incomes = entryDAO.getEntries(loggedInUser.getId(), flag,month,year );
        }else{
            month = calendar.get(Calendar.MONTH) + 1;
            year = calendar.get(Calendar.YEAR);
            incomes = entryDAO.getEntries(loggedInUser.getId(), flag, month, year);
        }
        float totalAmount = 0;
        for(Entry income:incomes){
            totalAmount+=income.getAmount();
        }
        response.addObject("totalAmount",totalAmount);
        List<Budget> budgets = budgetDAO.getBudgetEntries(loggedInUser.getId());
        float budgetedAmount =0;
        for(Budget budget:budgets){
            budgetedAmount+= budget.getAmount();
        }
        response.addObject("budgetedAmount", budgetedAmount);
        float difference = totalAmount - budgetedAmount;
        log.debug("{}",difference);
        response.addObject("difference", difference);

        List<SummaryDTO> summaries = new ArrayList<>();
        for(Budget budget:budgets){
            SummaryDTO summaryDTO = new SummaryDTO();
            summaryDTO.setBudgetCategory(budget.getDescription());  ;
            summaryDTO.setBudgetAmount(budget.getAmount());
            float totalExpense = 0;
            List<BudgetEntry> budgetEntry = budgetEntryDAO.getAllByMonthAndYearAndBudgetId(month,year,budget.getId());
            for(BudgetEntry bE:budgetEntry){
                int expenseId= bE.getExpenseId();
                Entry expense = entryDAO.findById(expenseId);
                totalExpense += expense.getAmount();
            }
            summaryDTO.setTotalExpense(totalExpense);
            summaryDTO.setDifference(budget.getAmount()-totalExpense);
            summaries.add(summaryDTO);
            }

        response.addObject("summaries", summaries);
        return response;
    }



}
