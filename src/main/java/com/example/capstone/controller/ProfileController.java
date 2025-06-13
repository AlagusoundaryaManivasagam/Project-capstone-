package com.example.capstone.controller;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.dao.BudgetEntryDAO;
import com.example.capstone.database.dao.EntryDAO;
import com.example.capstone.database.dao.UserDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.Entry;
import com.example.capstone.database.entity.User;
import com.example.capstone.dto.DateDTO;
import com.example.capstone.dto.ProfileDTO;
import com.example.capstone.security.AuthenticatedUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Controller
public class ProfileController {

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
    private UserDAO userDAO;

    @GetMapping("/profile/profile")
    public ModelAndView profile() {
        ModelAndView response = new ModelAndView();
        response.setViewName("profile/profile");
        User loggedInUser = authenticatedUserService.loadCurrentUser();
        response.addObject("username", loggedInUser.getUserName());
        LocalDate localDate = LocalDate.now();
        List<DateDTO> monthsYears = new ArrayList<>();
        for(int i=0;i<6;i++) {
            DateDTO dateDTO = new DateDTO();
            LocalDate date = localDate.minusMonths(i);
            dateDTO.setMonth(date.getMonthValue());
            dateDTO.setYear(date.getYear());
            monthsYears.add(dateDTO);
        }
        

        List<ProfileDTO> profileDTOs = new ArrayList<>();
        for(DateDTO monthYear : monthsYears) {
            String flag = "i";
            List<Entry> incomes = entryDAO.getEntries(loggedInUser.getId(),flag,monthYear.getMonth(),monthYear.getYear());
            float totalIncome = 0;
            for(Entry income : incomes) {
                totalIncome += income.getAmount();
            }
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setTotalIncome(totalIncome);
            List<Budget> budgets = budgetDAO.getMonthBudgetEntries(loggedInUser.getId(), monthYear.getMonth(), monthYear.getYear());
            float budgetedAmount =0;
            for(Budget budget:budgets){
               budgetedAmount+= budget.getAmount();
            }
            profileDTO.setBudgetedAmount(budgetedAmount);
            flag = "e";
            List<Entry> expenses = entryDAO.getEntries(loggedInUser.getId(),flag,monthYear.getMonth(),monthYear.getYear());
            float totalExpense = 0;
            for(Entry expense : expenses){
                totalExpense += expense.getAmount();
            }
            profileDTO.setTotalExpense(totalExpense);
            profileDTO.setBalance(totalIncome-totalExpense);
            String monthName = Month.of(monthYear.getMonth()).name();
            monthName = monthName.charAt(0) + monthName.substring(1).toLowerCase();
            profileDTO.setMonth(monthName);
            profileDTO.setYear(monthYear.getYear());
            profileDTOs.add(profileDTO);
        }
        response.addObject("profileDTOs", profileDTOs);

        return response;
    }
}
