package ru.gpbitfactory.minibank.middle.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Setter
@Getter
public class ErrorResponse {

    private Integer status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatusCode status, String message, LocalDateTime timestamp) {
        this.status = status.value();
        this.message = message;
        this.timestamp = timestamp;
    }
}
