package ru.gpbitfactory.minibank.middle.getclient.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseV2Inner;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ClientAccountMapper {

    private final ModelMapper modelMapper;

    public ClientAccount map(AccountsListResponseV2Inner accountsResponse) {
        var account = modelMapper.map(accountsResponse, ClientAccount.class);
        if (account.getAmount() == null) {
            account.setAmount(BigDecimal.ZERO);
        }
        return account;
    }
}
