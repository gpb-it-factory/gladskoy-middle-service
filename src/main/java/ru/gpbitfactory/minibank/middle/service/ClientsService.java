package ru.gpbitfactory.minibank.middle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.middle.client.BackendRestClient;
import ru.gpbitfactory.minibank.middle.domain.Client;
import ru.gpbitfactory.minibank.middle.exception.CreateClientException;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final BackendRestClient restClient;

    public Client save(Client client) {
        return restClient.createNewClient(client).orElseThrow(() -> {
            var message = String.format("Клиент c юзернеймом %s уже зарегистрирован", client.getUsername());
            return new CreateClientException(HttpStatus.UNPROCESSABLE_ENTITY, message);
        });
    }

    public Client getById(long id) {
        return restClient.getClient(id).orElseThrow(() -> {
            var message = String.format("Клиент c id %s не зарегистрирован", id);
            return new CreateClientException(HttpStatus.NOT_FOUND, message);
        });
    }
}
