package org.example.odc.validator;

import org.example.odc.validator.annotation.ValidDateDebutFin;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.web.dto.request.PromoUpdateDTORequest;

import java.time.LocalDate;

public class DateDebutFinValidator implements ConstraintValidator<ValidDateDebutFin, PromoUpdateDTORequest> {

    @Override
    public boolean isValid(PromoUpdateDTORequest promoDTORequest, ConstraintValidatorContext context) {
        LocalDate dateDebut = promoDTORequest.getDateDebut();
        LocalDate dateFin = promoDTORequest.getDateFin();

        if (dateDebut == null || dateFin == null) {
            return true;
        }

        if (dateDebut.isAfter(dateFin)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("La date de début doit être antérieure ou égale à la date de fin.")
                    .addConstraintViolation();
            return false; // Validation échouée
        }

        return true;
    }
}
