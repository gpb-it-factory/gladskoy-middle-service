package ru.gpbitfactory.minibank.middle.createclient.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequestV2;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;

    public CreateUserRequest map(CreateClientRequest createClientRequest) {
        return modelMapper.map(createClientRequest, CreateUserRequest.class);
    }

    public CreateUserRequestV2 map(CreateClientRequestV2 createClientRequest) {
        return modelMapper.map(createClientRequest, CreateUserRequestV2.class);
    }
}
