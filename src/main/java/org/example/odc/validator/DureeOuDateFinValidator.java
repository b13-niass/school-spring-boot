package org.example.odc.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.odc.validator.annotation.ValidDureeOuDateFin;
import org.example.odc.web.dto.request.PromoDTORequest;

import java.time.LocalDate;
import java.time.Period;

public class DureeOuDateFinValidator implements ConstraintValidator<ValidDureeOuDateFin, PromoDTORequest> {

    @Override
    public boolean isValid(PromoDTORequest promoDTORequest, ConstraintValidatorContext context) {
        LocalDate dateDebut = promoDTORequest.getDateDebut();
        LocalDate dateFin = promoDTORequest.getDateFin();
        int dureeReel = promoDTORequest.getDureeReel();

        boolean isDateFinPresent = dateFin != null;
        boolean isDureeReelPresent = dureeReel > 0;

        if (!isDateFinPresent && !isDureeReelPresent) {
            return false;
        }

        if (isDateFinPresent && isDureeReelPresent) {
            int monthsBetween = (int) Period.between(dateDebut, dateFin).toTotalMonths();
            return monthsBetween == dureeReel;
        }

        return true;
    }
}
