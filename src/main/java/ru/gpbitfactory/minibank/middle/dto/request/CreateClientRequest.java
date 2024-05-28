package ru.gpbitfactory.minibank.middle.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientRequest {

    @NotNull(message = "не может быть пустым")
    @Positive(message = "может быть только положительным целым числом больше 1")
    @Digits(message = "может содержать только число от 1 до 16 символов", integer = 16, fraction = 0)
    private Long telegramUserId;
}
