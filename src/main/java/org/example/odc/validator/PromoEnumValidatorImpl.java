package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.validator.annotation.PromoEnumValidator;

import java.util.Arrays;

public class PromoEnumValidatorImpl implements ConstraintValidator<PromoEnumValidator, String> {

    private Enum<?>[] enumValues;

    @Override
    public void initialize(PromoEnumValidator constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle nulls
        }

        // Check if the provided value exists in the enum
        return Arrays.stream(enumValues)
                .anyMatch(enumValue -> enumValue.name().equals(value));
    }
}
