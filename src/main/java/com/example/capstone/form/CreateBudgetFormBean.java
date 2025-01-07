package com.example.capstone.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBudgetFormBean {

    private Integer id;

    private String description;

    private float amount;

    private Integer userId;
}
