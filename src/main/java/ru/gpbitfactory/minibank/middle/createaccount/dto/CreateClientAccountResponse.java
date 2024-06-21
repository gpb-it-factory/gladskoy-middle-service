package ru.gpbitfactory.minibank.middle.createaccount.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Информация об успешном открытии счёта для клиента
 */
@Setter
@Getter
@Builder
public class CreateClientAccountResponse {

    private String message;
}
