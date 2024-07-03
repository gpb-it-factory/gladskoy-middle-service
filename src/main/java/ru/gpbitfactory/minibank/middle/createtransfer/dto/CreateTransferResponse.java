package ru.gpbitfactory.minibank.middle.createtransfer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Информация об успешном переводе между счетами клиентов
 */
@Setter
@Getter
@Builder
public class CreateTransferResponse {

    private String message;
}
