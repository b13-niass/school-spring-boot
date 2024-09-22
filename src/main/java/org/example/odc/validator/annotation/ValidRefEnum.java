package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.RefEnumValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RefEnumValidator.class)
public @interface ValidRefEnum {

    Class<? extends Enum<?>> enumClass();

    String message() default "Invalid value. This is not permitted.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
