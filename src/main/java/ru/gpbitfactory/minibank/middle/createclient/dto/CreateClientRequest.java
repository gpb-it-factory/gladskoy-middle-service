package ru.gpbitfactory.minibank.middle.createclient.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Запрос на создание нового клиента
 */
@Setter
@Getter
public class CreateClientRequest {

    @NotNull(message = "не может быть пустым")
    @Positive(message = "может быть только положительным целым числом больше 1")
    @Digits(message = "может содержать только число от 1 до 16 символов", integer = 16, fraction = 0)
    private Long telegramUserId;
}
