package ru.gpbitfactory.minibank.middle.dto.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.middle.domain.Client;
import ru.gpbitfactory.minibank.middle.dto.request.ClientRequest;
import ru.gpbitfactory.minibank.middle.dto.response.ClientBalanceResponse;
import ru.gpbitfactory.minibank.middle.dto.response.ClientResponse;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;

    public Client toClient(ClientRequest dto) {
        return modelMapper.map(dto, Client.class);
    }

    public ClientResponse toClientResponse(Client dao) {
        return modelMapper.map(dao, ClientResponse.class);
    }

    public ClientBalanceResponse toClientBalanceResponse(Client dao) {
        return modelMapper.map(dao, ClientBalanceResponse.class);
    }
}
