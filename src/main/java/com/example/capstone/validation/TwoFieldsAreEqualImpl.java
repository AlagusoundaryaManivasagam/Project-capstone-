package com.example.capstone.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class TwoFieldsAreEqualImpl implements ConstraintValidator<TwoFieldsAreEqual, Object> {

    private String fieldOneName;
    private String fieldTwoName;

    @Override
    public void initialize(TwoFieldsAreEqual constraintAnnotation) {
        fieldOneName = constraintAnnotation.fieldOneName();
        fieldTwoName = constraintAnnotation.fieldTwoName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

//         <dependency>
//                <groupId>commons-beanutils</groupId>
//                <artifactId>commons-beanutils</artifactId>
//                <version>1.9.4</version>
//                </dependency>

        try {
//            final String fieldOneValue = BeanUtils.getProperty(value, fieldOneName);
//            final String fieldTwoValue = BeanUtils.getProperty(value, fieldTwoName);

            // both are null
//            if (fieldOneValue == null && fieldTwoValue == null) {
//                return true;
//            }
//
//            // if the are equal then return true
//            if (fieldOneValue.equals(fieldTwoValue)) {
//                return true;
//            }

        } catch (Exception e) {
            // do nothing
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(fieldOneName)
                .addConstraintViolation();

        return false;
    }

}
