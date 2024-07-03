package ru.gpbitfactory.minibank.middle.createtransfer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Запрос на перевод денежных средств между счетами клиентов
 */
@Setter
@Getter
public class CreateTransferRequest {

    @NotBlank(message = "не может быть пустым")
    private String from;

    @NotBlank(message = "не может быть пустым")
    private String to;

    @NotNull(message = "не может быть пустым")
    @Positive(message = "может быть только положительным целым числом больше 1")
    private BigDecimal amount;
}
