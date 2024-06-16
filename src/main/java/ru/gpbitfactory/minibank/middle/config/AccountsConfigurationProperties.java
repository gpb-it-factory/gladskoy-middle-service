package ru.gpbitfactory.minibank.middle.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Validated
@ConfigurationProperties("backend")
public class AccountsConfigurationProperties {

    /**
     * Список счетов, доступных клиенту для открытия, опционально
     */
    private List<Account> accounts;

    @Setter
    @Getter
    public static class Account {

        /**
         * Наименование счёта, обязательно
         */
        @NotNull
        private String name;

        /**
         * Описание счёта, опционально
         */
        private String description;

        /**
         * Начальный баланс счёта, опционально
         */
        @Min(value = 0)
        private BigDecimal initAmount;

        /**
         * Тип счёта, обязательно
         */
        @NotNull
        private AccountType type;
    }

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
