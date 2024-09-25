package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.PromoReferentielRepository;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.validator.annotation.ActiveReferentiels;
import org.example.odc.web.dto.request.PromoUpdateRefDTORequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActiveReferentielsValidator implements ConstraintValidator<ActiveReferentiels, PromoUpdateRefDTORequest> {

    @Autowired
    private ReferentielRepository referentielRepository;

    @Autowired
    private PromoReferentielRepository promoReferentielRepository;

    @Override
    public boolean isValid(PromoUpdateRefDTORequest request, ConstraintValidatorContext context) {
        List<Long> referentielIdsToAdd = request.getReferentielIdsToAdd();
        Long promoId = request.getPromoId();
//

        if (referentielIdsToAdd == null || referentielIdsToAdd.isEmpty()) {
            return true;
        }

        for (Long referentielId : referentielIdsToAdd) {
            boolean isActive = referentielRepository.existsByIdAndStatusActive(referentielId);
            boolean isAlreadyAssociated = promoReferentielRepository.existsByPromoIdAndReferentielId(promoId, referentielId);

            if (!isActive) {
                return false;
            }

            if (isAlreadyAssociated) {
                return false;
            }
        }

        return true;
    }
}
