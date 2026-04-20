package com.example.capstone.dto;

import com.example.capstone.database.entity.Budget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BudgetDetailDTO {
    private List<Budget> budgets;
    private int size;
    private String[] months;
    private int month;
    private int year;
    private List<Integer> years;
    private String message;
}
