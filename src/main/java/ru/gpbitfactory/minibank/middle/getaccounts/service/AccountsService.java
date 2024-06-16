package ru.gpbitfactory.minibank.middle.getaccounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.middle.config.AccountsConfigurationProperties;
import ru.gpbitfactory.minibank.middle.config.AccountsConfigurationProperties.Account;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsConfigurationProperties accountsConfigurationProperties;

    public List<Account> getAccounts() {
        return accountsConfigurationProperties.getAccounts();
    }
}
