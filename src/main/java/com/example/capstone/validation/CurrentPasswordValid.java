package com.example.capstone.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CurrentPasswordValidImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentPasswordValid {

    String message() default "{CurrentPasswordValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}