package com.vitalii.userservicemanagment.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LatinLetterValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LatinLetter {
    String message() default "There should be only latin letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
