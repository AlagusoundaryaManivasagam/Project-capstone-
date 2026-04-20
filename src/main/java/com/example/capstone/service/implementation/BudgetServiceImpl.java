package com.example.capstone.service.implementation;

import com.example.capstone.database.dao.BudgetDAO;
import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.User;
import com.example.capstone.dto.BudgetDetailDTO;
import com.example.capstone.form.CreateBudgetFormBean;
import com.example.capstone.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public class BudgetServiceImpl implements BudgetService {
    @Autowired
    private BudgetDAO budgetDAO;
    @Autowired
    private DateServiceImpl dateService;
    @Override
    public BudgetDetailDTO createOrUpdateBudget(CreateBudgetFormBean form, User user) {
        Budget budget = null;
        if(form.getId() != null) {
            budget = budgetDAO.findById(form.getId());
        }
        String message = null;
        if(budget == null){
            budget = new Budget();
        }else{
            message = "Budget edited";
        }
        budget.setAmount(form.getAmount());
        budget.setDescription(form.getDescription());
        budget.setUser(user);

        int monthValue = Month
                .valueOf(form.getMonth().trim().toUpperCase())
                .getValue();
        budget.setMonth(monthValue);

        budget.setYear(form.getYear());
        budgetDAO.save(budget);

        // 5) reload month budgets for UI
        int month = budget.getMonth();
        int year = budget.getYear();

        List<Budget> budgets = budgetDAO.getMonthBudgetEntries(user.getId(), month, year);

        // 6) build model for view
        BudgetDetailDTO model = new BudgetDetailDTO();
        model.setBudgets(budgets);
        model.setSize(budgets.size());

        model.setMonths(dateService.giveMonths()); // from your dateServiceImpl
        model.setMonth(month);
        model.setYear(year);
        model.setYears(dateService.giveYears());
        model.setMessage(message);

        return model;
    }
}
