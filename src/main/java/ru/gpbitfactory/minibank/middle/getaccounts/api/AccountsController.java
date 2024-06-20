package ru.gpbitfactory.minibank.middle.getaccounts.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.getaccounts.dto.Account;
import ru.gpbitfactory.minibank.middle.getaccounts.dto.AccountsMapper;
import ru.gpbitfactory.minibank.middle.getaccounts.service.AccountsService;

import java.util.List;

/**
 *  API для сервиса <a href="https://github.com/gpb-it-factory/gladskoy-telegram-bot">gladskoy-telegram-bot</a>.
 *  Предоставляет операции для работы со счетами клиентов Мини-банка.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

    private final AccountsService accountsService;
    private final AccountsMapper accountsMapper;

    /**
     * Получение списка счетов, доступных для открытия.
     * @return список счетов.
     */
    @GetMapping
    public ResponseEntity<List<Account>> getAvailableAccounts() {
        var accountList = accountsService.getAccounts().stream()
                .map(accountsMapper::map)
                .toList();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }
}
