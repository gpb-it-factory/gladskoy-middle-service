package ru.gpbitfactory.minibank.middle.createaccount.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Запрос на открытие нового счёта для клиента
 */
@Getter
@Setter
public class CreateClientAccountRequest {

    @NotBlank(message = "не может быть пустым")
    private String accountName;
}
