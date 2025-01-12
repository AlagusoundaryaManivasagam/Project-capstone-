package com.example.capstone.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CreateEntryFormBean {
    private Integer id;

    @Length(max = 100, message = "Description must be less than 100 characters")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private float amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private Integer userId;

    private String flag;
}
