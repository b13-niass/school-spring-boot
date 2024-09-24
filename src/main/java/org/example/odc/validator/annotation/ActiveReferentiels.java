package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.ActiveReferentielsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ActiveReferentielsValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ActiveReferentiels {
    String message() default "Certains des référentiels sélectionnés ne sont pas actifs.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
