package org.teamone.onlinestorebuyreadreview.http.handler.api.error;

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
public class ApiValidationErrorMapper implements Mapper<BindingResult, List<ApiSubError>> {

    @Override
    public List<ApiSubError> map(BindingResult bindingResult) {
        List<ApiSubError> apiValidationErrors = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            apiValidationErrors.add(
                    ApiValidationError.builder()
                            .object(bindingResult.getObjectName())
                            .field(fieldError.getField())
                            .rejectedValue(fieldError.getRejectedValue())
                            .message(fieldError.getDefaultMessage())
                            .build());
        }
        return apiValidationErrors;
    }
}
