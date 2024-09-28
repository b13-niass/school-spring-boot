package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.validator.annotation.ValidEmail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
            return false;
        }

        return !userRepository.existsByEmail(email);
    }
}