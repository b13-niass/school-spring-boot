package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.data.repository.ReferentielRepository;
import org.example.odc.enums.ReferentielStatusEnum;
import org.example.odc.validator.annotation.ValidReferentielIds;
import org.example.odc.web.dto.request.PromoDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReferentielIdsValidator implements ConstraintValidator<ValidReferentielIds, List<Long>> {

    @Autowired
    private ReferentielRepository referentielRepository;

    @Override
    public boolean isValid(List<Long> referentielIds, ConstraintValidatorContext context) {
        if (referentielIds == null || referentielIds.isEmpty()) {
            return true;
        }

        return referentielRepository.existsByIdInAndStatusIs(referentielIds, ReferentielStatusEnum.ACTIF);
    }
}
