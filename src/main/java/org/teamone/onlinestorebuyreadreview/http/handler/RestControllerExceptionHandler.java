package org.teamone.onlinestorebuyreadreview.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.teamone.onlinestorebuyreadreview.http.handler.api.error.ApiErrorResponse;
import org.teamone.onlinestorebuyreadreview.http.handler.api.error.ApiSubError;
import org.teamone.onlinestorebuyreadreview.http.handler.api.error.ApiValidationError;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiErrorException;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiSubErrorException;
import org.teamone.onlinestorebuyreadreview.http.handler.api.exception.ApiValidationException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Slf4j
@RestControllerAdvice(basePackages = "org.teamone.onlinestorebuyreadreview.http.controller")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiErrorException.class)
    private ResponseEntity<Object> buildResponseEntity(ApiErrorException apiErrorException) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(apiErrorException.getStatus())
                .message(apiErrorException.getMessage())
                .timestamp(apiErrorException.getTimestamp())
                .errorDetails(mapApiSubExceptionToErrorDetails(apiErrorException))
                .build();
        return new ResponseEntity<>(apiErrorResponse, apiErrorException.getStatus());
    }

    private List<ApiSubError> mapApiSubExceptionToErrorDetails(ApiErrorException apiErrorException) {
        List<ApiSubError> apiSubErrorList = new ArrayList<>();
        for (ApiSubErrorException exceptionDetail : apiErrorException.getExceptionDetails()) {
            if (exceptionDetail.getClass().equals(ApiValidationException.class)) {
                ApiValidationException apiValidationException = (ApiValidationException) exceptionDetail;
                apiSubErrorList.add(ApiValidationError.builder()
                        .object(apiValidationException.getObject())
                        .field(apiValidationException.getObject())
                        .message(apiValidationException.getMessage())
                        .rejectedValue(apiValidationException.getRejectedValue())
                        .build());
            } else {
                throw new RuntimeException("Unknown type of exception: " + exceptionDetail.getClass());
            }
        }

        return apiSubErrorList;
    }
}
