package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.validator.annotation.NotEmptyStringIfPresent;

import java.util.List;

public class NotEmptyStringIfPresentValidator implements ConstraintValidator<NotEmptyStringIfPresent, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // If the list is null, it's valid
        if (value == null) {
            return true;
        }
        // If the list is provided, it should not be empty
        return !value.isEmpty();
    }
}