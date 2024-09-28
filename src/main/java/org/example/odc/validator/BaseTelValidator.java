package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.validator.annotation.ValidBaseTel;
import org.example.odc.validator.annotation.ValidTelephone;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class BaseTelValidator implements ConstraintValidator<ValidBaseTel, String> {

    private static final String TELEPHONE_PATTERN = "^(77|76|78)\\d{7}$";

    @Override
    public void initialize(ValidBaseTel constraintAnnotation) {
    }

    @Override
    public boolean isValid(String telephone, ConstraintValidatorContext context) {
        return telephone != null && Pattern.compile(TELEPHONE_PATTERN).matcher(telephone).matches();
    }
}