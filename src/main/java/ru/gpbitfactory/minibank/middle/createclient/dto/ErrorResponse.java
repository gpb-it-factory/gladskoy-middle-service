package ru.gpbitfactory.minibank.middle.createclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Builder
public class ErrorResponse {

    private String message;
    private String type;
    private String code;
    private UUID traceId;
}
