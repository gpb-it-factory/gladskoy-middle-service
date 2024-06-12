package ru.gpbitfactory.minibank.middle.createclient.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * Запрос на создание нового клиента
 */
@Setter
@Getter
public class CreateClientRequestV2 {

    @NotNull(message = "не может быть пустым")
    @Positive(message = "может быть только положительным целым числом больше 1")
    @Digits(message = "может содержать только число от 1 до 16 символов", integer = 16, fraction = 0)
    private Long telegramUserId;

    @NotNull(message = "не может быть пустым")
    @Pattern(regexp = "^\\w{3,}$", message = "может содержать только латинские буквы и цифры, минимум 3 символа")
    private String telegramUserName;
}
