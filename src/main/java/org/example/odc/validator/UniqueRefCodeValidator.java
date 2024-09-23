package org.example.odc.validator;

import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.validator.annotation.UniqueRefCode;
import org.example.odc.validator.annotation.UniqueRefLibelle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueRefCodeValidator implements ConstraintValidator<UniqueRefCode, String> {

    @Autowired
    private ReferentielRepository referentielRepository;

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (code == null) {
            return true;
        }
        return !referentielRepository.existsByCode(code);
    }
}
