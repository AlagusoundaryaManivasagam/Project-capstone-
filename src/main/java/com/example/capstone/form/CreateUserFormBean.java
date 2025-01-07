package com.example.capstone.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateUserFormBean {

    private Integer id;

    @Pattern(regexp = ".+@.+\\..+", message = "Invalid email form")
    @Length(max = 100, message = "Email must be less than 100 characters")
    @NotEmpty(message = "Email is required")
    private String email;

    @Length(max = 50, message = "User Name must be less than 50 characters")
    @NotEmpty(message = "User Name is required")
    private String username;

    @Length(max = 100, message = "Password must be less than 100 characters")
    @NotEmpty(message = "Password is required")
    private String password;

    @Length(max = 100, message = "Password must be less than 100 characters")
    @NotEmpty(message = "Please retype the password")
    private String confirmPassword;
}
