package org.teamone.onlinestorebuyreadreview.http.handler.api.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
public class ApiErrorException extends RuntimeException{
    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubErrorException> exceptionDetails;

    private ApiErrorException() {
        timestamp = LocalDateTime.now();
    }

    public ApiErrorException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorException(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiErrorException(HttpStatus status, String message, List<ApiSubErrorException> exceptionDetails) {
        this();
        this.status = status;
        this.message = message;
        this.exceptionDetails = exceptionDetails;
    }
}
