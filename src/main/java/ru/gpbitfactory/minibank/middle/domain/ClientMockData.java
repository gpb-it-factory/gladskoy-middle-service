package ru.gpbitfactory.minibank.middle.domain;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class ClientMockData {

    private static final double INIT_BALANCE = 5000D;
    private final List<Client> clientRegistry = new ArrayList<>();
    private int userIdSequence;

    private int getNextId() {
        return ++userIdSequence;
    }

    public Optional<Client> save(Client clientRequest) {
        var client = clientRegistry.stream()
                .filter(c -> c.getUsername().equals(clientRequest.getUsername()))
                .findFirst();

        if (client.isPresent()) {
            return Optional.empty();
        }

        var newClient = Client.builder()
                .id(getNextId())
                .username(clientRequest.getUsername())
                .currentBalance(INIT_BALANCE)
                .build();

        clientRegistry.add(newClient);

        return Optional.ofNullable(newClient);
    }

    public Optional<Client> getById(long clientId) {
        return clientRegistry.stream()
                .filter(c -> c.getId() == clientId)
                .findFirst();
    }
}
