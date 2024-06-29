package ru.gpbitfactory.minibank.middle.getclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Данные о счете клиента
 */
@Setter
@Getter
public class ClientAccount {

    private String name;
    private BigDecimal amount;
}
