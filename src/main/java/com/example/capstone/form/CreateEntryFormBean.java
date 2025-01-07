package com.example.capstone.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateEntryFormBean {
    private Integer id;

    private String description;

    private float amount;

    private Date date;

    private Integer userId;

    private String flag;
}
