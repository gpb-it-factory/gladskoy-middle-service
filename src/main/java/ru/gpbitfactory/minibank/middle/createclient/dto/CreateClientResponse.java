package ru.gpbitfactory.minibank.middle.createclient.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Информация о клиенте
 */
@Setter
@Getter
@Builder
public class CreateClientResponse {

    String message;
}
