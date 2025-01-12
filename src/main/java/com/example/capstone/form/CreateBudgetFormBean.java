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
    private float amount;

    private Integer userId;
}
