package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.ReferentielIdsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ReferentielIdsValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidReferentielIds {
    String message() default "L'un des ids de référentiel est invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

