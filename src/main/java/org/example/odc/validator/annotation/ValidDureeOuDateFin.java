package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.DureeOuDateFinValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DureeOuDateFinValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDureeOuDateFin {
    String message() default "la dateFin ou la dureeReel doit etre donnée.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
