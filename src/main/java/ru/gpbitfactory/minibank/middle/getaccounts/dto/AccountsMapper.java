package ru.gpbitfactory.minibank.middle.getaccounts.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.middle.config.AccountsConfigurationProperties;

@Component
@RequiredArgsConstructor
public class AccountsMapper {

    private final ModelMapper modelMapper;

    public Account map(AccountsConfigurationProperties.Account accountConfiguration) {
        return modelMapper.map(accountConfiguration, Account.class);
    }
}
