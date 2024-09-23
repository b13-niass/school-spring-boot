package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.CompetenceRepository;
import org.example.odc.data.repository.ModuleRepository;
import org.example.odc.validator.annotation.UniqueCompetenceName;
import org.example.odc.validator.annotation.UniqueModuleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueModuleNameValidator implements ConstraintValidator<UniqueModuleName, String> {

    @Autowired
    private ModuleRepository moduleRepository;

    @Override
    public boolean isValid(String nom, ConstraintValidatorContext context) {
        if (nom == null) {
            return true;
        }
        return !moduleRepository.existsByNom(nom);
    }
}
