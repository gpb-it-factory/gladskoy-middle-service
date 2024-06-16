package ru.gpbitfactory.minibank.middle.getaccounts.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Счёт, доступный клиенту для открытия
 */
@Setter
@Getter
public class Account {

    /**
     * Наименование счёта, обязательно
     */
    private String name;

    /**
     * Описание счёта, опционально
     */
    private String description;

    /**
     * Начальный баланс счёта, опционально
     */
    private BigDecimal initAmount;

    /**
     * Тип счёта, обязательно
     */
    private AccountType type;

    public enum AccountType {
        /**
         * Стандартный счёт, рекомендуется открывать уже существующим клиентам
         */
        COMMON,

        /**
         * Акционный счёт, рекомендуется отрывать новым клиентам
         */
        PROMO,

        /**
         * Счёт, который открывается по-умолчанию для новых клиентов, если сейчас не проходит ни каких акций
         */
        DEFAULT;
    }
}
