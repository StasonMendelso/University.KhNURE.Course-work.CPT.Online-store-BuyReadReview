package org.teamone.onlinestorebuyreadreview.http.handler.api.exception;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.teamone.onlinestorebuyreadreview.util.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Component
public class ApiValidationExceptionMapper implements Mapper<BindingResult, List<ApiValidationException>> {
    @Override
    public List<ApiValidationException> map(BindingResult bindingResult) {
        List<ApiValidationException> apiValidationErrors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            apiValidationErrors.add(
                    ApiValidationException.builder()
                            .object(bindingResult.getObjectName())
                            .field(fieldError.getField())
                            .rejectedValue(fieldError.getRejectedValue())
                            .message(fieldError.getDefaultMessage())
                            .build());
        }
        return apiValidationErrors;
    }
}
