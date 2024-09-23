package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.PromoRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.validator.annotation.UniquePromoLibelle;
import org.example.odc.validator.annotation.UniqueRefCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniquePromoLibelleValidator implements ConstraintValidator<UniquePromoLibelle, String> {

    @Autowired
    private PromoRepository promoRepository;

    @Override
    public boolean isValid(String Libelle, ConstraintValidatorContext context) {
        if (Libelle == null) {
            return true;
        }
        return !promoRepository.existsByLibelle(Libelle);
    }
}
