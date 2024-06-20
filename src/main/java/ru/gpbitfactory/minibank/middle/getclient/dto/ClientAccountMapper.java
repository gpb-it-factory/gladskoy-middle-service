package ru.gpbitfactory.minibank.middle.getclient.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseV2Inner;

@Component
@RequiredArgsConstructor
public class ClientAccountMapper {

    private final ModelMapper modelMapper;

    public ClientAccount map(AccountsListResponseV2Inner accountsResponse) {
        return modelMapper.map(accountsResponse, ClientAccount.class);
    }
}
