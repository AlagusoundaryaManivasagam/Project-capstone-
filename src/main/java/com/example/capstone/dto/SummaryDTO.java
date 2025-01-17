package com.example.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SummaryDTO {
    private String budgetCategory;

    private float budgetAmount;

    private float totalExpense;

    private float difference;
}
