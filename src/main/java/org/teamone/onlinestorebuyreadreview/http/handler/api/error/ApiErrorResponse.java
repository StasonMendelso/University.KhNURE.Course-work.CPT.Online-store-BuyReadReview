package org.teamone.onlinestorebuyreadreview.http.handler.api.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Stanislav Hlova
 */
@Data
@AllArgsConstructor
@Builder
public class ApiErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private Integer code;
    private String message;
    @JsonIgnore
    private String debugMessage;
    private List<ApiSubError> errorDetails;

    private ApiErrorResponse() {
        timestamp = LocalDateTime.now();
    }

    public static class ApiErrorResponseBuilder {
        public ApiErrorResponseBuilder status(HttpStatus httpStatus) {
            this.status = httpStatus;
            this.code = httpStatus.value();
            return this;
        }
    }
}
