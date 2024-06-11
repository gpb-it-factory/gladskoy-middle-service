package ru.gpbitfactory.minibank.middle.createclient.dto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;

    public CreateUserRequest map(CreateClientRequest createClientRequest) {
        return modelMapper.map(createClientRequest, CreateUserRequest.class);
    }
}
