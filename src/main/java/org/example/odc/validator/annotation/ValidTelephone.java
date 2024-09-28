package org.example.odc.validator.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.TelephoneValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TelephoneValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTelephone {
    String message() default "Numéro de téléphone invalide ou déjà existant";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
