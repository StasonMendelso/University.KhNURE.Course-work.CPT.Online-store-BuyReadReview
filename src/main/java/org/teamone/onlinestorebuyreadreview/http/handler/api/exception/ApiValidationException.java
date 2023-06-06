package org.teamone.onlinestorebuyreadreview.http.handler.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ApiValidationException extends ApiSubErrorException{
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
