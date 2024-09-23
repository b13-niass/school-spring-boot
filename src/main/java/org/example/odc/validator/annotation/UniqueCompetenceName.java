package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.UniqueCompetenceNameValidator;
import org.example.odc.validator.UniqueRefCodeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueCompetenceNameValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCompetenceName {
    String message() default "Le nom de la compétence doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
