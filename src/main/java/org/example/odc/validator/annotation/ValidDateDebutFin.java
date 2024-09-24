package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.DateDebutFinValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateDebutFinValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateDebutFin {
    String message() default "La date de début doit être antérieure ou égale à la date de fin si les deux sont fournies.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

