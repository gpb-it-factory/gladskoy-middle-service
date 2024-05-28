package ru.gpbitfactory.minibank.middle.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.backend.api.UsersApiClient;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.middle.exception.CreateClientError;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientsService {

    private final UsersApiClient usersApiClient;

    public void createClient(CreateUserRequest request) {
        var telegramUserId = request.getUserId();
        log.debug("Поступил запрос на создание клиента c telegramUserId: {}", telegramUserId);
        var response = usersApiClient.createUser(request);
        var responseStatusCode = response.getStatusCode().value();

        if (!response.getStatusCode().is2xxSuccessful()) {
            var errorMessage = String.format("Не удалось создать клиента с telegramUserId: %s, backend-service status code: %s",
                    telegramUserId, responseStatusCode);
            throw new CreateClientError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }

        log.debug("Клиент c telegramUserId: {} успешно создан", telegramUserId);
    }
}
