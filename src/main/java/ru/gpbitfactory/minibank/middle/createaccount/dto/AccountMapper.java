package ru.gpbitfactory.minibank.middle.createaccount.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequestV2;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final ModelMapper modelMapper;

    public CreateAccountRequestV2 map(CreateClientAccountRequest createClientAccountRequest) {
        return modelMapper.map(createClientAccountRequest, CreateAccountRequestV2.class);
    }
}
