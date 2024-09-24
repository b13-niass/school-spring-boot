package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.validator.annotation.NoApprenantsForReferentielsToRemove;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NoApprenantsForReferentielsValidator implements ConstraintValidator<NoApprenantsForReferentielsToRemove, List<Long>> {

    @Autowired
    private PromoReferentielRepository promoReferentielRepository;

    @Override
    public boolean isValid(List<Long> referentielIdsToRemove, ConstraintValidatorContext context) {
        if (referentielIdsToRemove == null || referentielIdsToRemove.isEmpty()) {
            return true;
        }

        for (Long referentielId : referentielIdsToRemove) {
            boolean hasApprenants = promoReferentielRepository.existsByReferentielIdAndApprenantsNotEmpty(referentielId);
            if (hasApprenants) {
                return false;
            }
        }

        return true;
    }
}
