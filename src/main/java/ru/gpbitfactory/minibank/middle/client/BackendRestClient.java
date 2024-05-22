package ru.gpbitfactory.minibank.middle.client;

import ru.gpbitfactory.minibank.middle.domain.Client;

import java.util.Optional;

public interface BackendRestClient {

    Optional<Client> createNewClient(Client client);

    Optional<Client> getClient(long id);
}
