package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.CompetenceRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.validator.annotation.UniqueCompetenceName;
import org.example.odc.validator.annotation.UniqueRefCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueCompetenceNameValidator implements ConstraintValidator<UniqueCompetenceName, String> {

    @Autowired
    private CompetenceRepository competenceRepository;

    @Override
    public boolean isValid(String nom, ConstraintValidatorContext context) {
        if (nom == null) {
            return true;
        }
        return !competenceRepository.existsByNom(nom);
    }
}
