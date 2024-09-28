package org.example.odc.validator.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.EmailValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "Email invalide ou déjà existant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
