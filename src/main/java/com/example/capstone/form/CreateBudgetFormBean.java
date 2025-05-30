package com.example.capstone.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateBudgetFormBean {

    private Integer id;

    @Length(max = 100, message = "Description must be less than 100 characters")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private Float amount;

    private Integer userId;

    @NotNull(message = "Month cannot be empty")
    private String month;

    @NotNull(message = "Year cannot be empty")
    private Integer year;
}
