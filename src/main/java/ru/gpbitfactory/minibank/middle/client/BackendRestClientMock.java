package ru.gpbitfactory.minibank.middle.client;

import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.middle.domain.Client;
import ru.gpbitfactory.minibank.middle.domain.ClientMockData;

import java.util.Optional;

@Component
public class BackendRestClientMock implements BackendRestClient {

    @Override
    public Optional<Client> createNewClient(Client client) {
        return ClientMockData.save(client);
    }

    @Override
    public Optional<Client> getClient(long id) {
        return ClientMockData.getById(id);
    }
}
