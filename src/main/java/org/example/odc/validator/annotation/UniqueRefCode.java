package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.UniqueRefCodeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueRefCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueRefCode {
    String message() default "Le code du référentiel doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
