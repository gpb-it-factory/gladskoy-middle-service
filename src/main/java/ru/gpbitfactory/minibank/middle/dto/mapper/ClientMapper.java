package ru.gpbitfactory.minibank.middle.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.middle.dto.request.CreateClientRequest;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;

    public CreateUserRequest map(CreateClientRequest createClientRequest) {
        return modelMapper.map(createClientRequest, CreateUserRequest.class);
    }
}
