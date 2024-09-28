package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.UserRepository;
import org.example.odc.validator.annotation.ValidTelephone;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class TelephoneValidator implements ConstraintValidator<ValidTelephone, String> {

    private static final String TELEPHONE_PATTERN = "^(77|76|78)\\d{7}$";

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(ValidTelephone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String telephone, ConstraintValidatorContext context) {
        if (telephone == null || !Pattern.compile(TELEPHONE_PATTERN).matcher(telephone).matches()) {
            return false;
        }

        return !userRepository.existsByTelephone(telephone);
    }
}