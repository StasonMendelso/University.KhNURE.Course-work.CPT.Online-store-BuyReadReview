package org.teamone.onlinestorebuyreadreview.util.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.teamone.onlinestorebuyreadreview.util.validation.annotation.NotEmptyList;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return (value!=null && !value.isEmpty());
    }
}
