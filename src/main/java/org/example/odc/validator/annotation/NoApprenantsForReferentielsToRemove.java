package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.NoApprenantsForReferentielsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoApprenantsForReferentielsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoApprenantsForReferentielsToRemove {
    String message() default "Le référentiel ne peut pas être retiré car il a des apprenants associés.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
