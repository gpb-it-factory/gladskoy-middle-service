package ru.gpbitfactory.minibank.middle.getclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Формат непредвиденной ошибки
 */
@Setter
@Getter
@Builder
public class ErrorResponse {

    private String message;
    private String type;
    private String code;
    private UUID traceId;
}
