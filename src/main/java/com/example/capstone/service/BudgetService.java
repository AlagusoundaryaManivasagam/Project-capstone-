package com.example.capstone.service;

import com.example.capstone.database.entity.Budget;
import com.example.capstone.database.entity.User;
import com.example.capstone.dto.BudgetDetailDTO;
import com.example.capstone.form.CreateBudgetFormBean;

public interface BudgetService {
    BudgetDetailDTO createOrUpdateBudget(CreateBudgetFormBean form, User use);
}
