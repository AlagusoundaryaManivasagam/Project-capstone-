package com.example.capstone.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileDTO {
    private float totalIncome;
    private float totalExpense;
    private float budgetedAmount;
    private float balance;
    private String month;
    private int year;
}
