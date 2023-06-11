package org.teamone.onlinestorebuyreadreview.util.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.teamone.onlinestorebuyreadreview.util.validation.validator.NotEmptyListValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Stanislav Hlova
 */
@Constraint(validatedBy = NotEmptyListValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface NotEmptyList {
    String message() default "List can't be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
