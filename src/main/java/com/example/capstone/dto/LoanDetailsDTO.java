package com.example.capstone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanDetailsDTO {
    private double mp;
    private double tp;
    private double i;
    private List<List<Object>> schedule;
}
