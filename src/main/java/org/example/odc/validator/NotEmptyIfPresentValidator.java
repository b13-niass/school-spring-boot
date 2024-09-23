package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.validator.annotation.NotEmptyIfPresent;

import java.util.List;

public class NotEmptyIfPresentValidator implements ConstraintValidator<NotEmptyIfPresent, List<?>> {

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        // If the list is null, it's valid
        if (value == null) {
            return true;
        }
        // If the list is provided, it should not be empty
        return !value.isEmpty();
    }
}