package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.validator.annotation.ValidRefEnum;

import java.util.Arrays;

public class RefEnumValidator implements ConstraintValidator<ValidRefEnum, String> {

    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidRefEnum constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // You can adjust this if you don't want null to be valid
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(e -> e.name().equals(value));
    }
}