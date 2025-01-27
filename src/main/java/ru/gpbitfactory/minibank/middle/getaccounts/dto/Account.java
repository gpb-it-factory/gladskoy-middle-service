package ru.gpbitfactory.minibank.middle.getaccounts.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Locale;

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

    private enum AccountType {
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

        @JsonValue
        public String getValue() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }
}
