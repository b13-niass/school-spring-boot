package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.UniquePromoLibelleValidator;
import org.example.odc.validator.UniqueRefLibelleValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePromoLibelleValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePromoLibelle {
    String message() default "Le libelle du référentiel doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}