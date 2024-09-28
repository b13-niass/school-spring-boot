package org.example.odc.validator.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.odc.validator.BaseTelValidator;
import org.example.odc.validator.TelephoneValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BaseTelValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBaseTel {
    String message() default "Numéro de téléphone invalide";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
