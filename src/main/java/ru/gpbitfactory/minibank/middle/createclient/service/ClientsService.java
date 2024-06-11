package ru.gpbitfactory.minibank.middle.createclient.service;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.backend.api.UsersApiClient;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.middle.createclient.exception.CreateClientError;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientsService {

    private final UsersApiClient usersApiClient;

    public void createClient(CreateUserRequest request) {
        var telegramUserId = request.getUserId();
        log.debug("Поступил запрос на создание клиента c telegramUserId: {}", telegramUserId);

        try {
            usersApiClient.createUser(request);
        } catch (FeignException e) {
            throw new CreateClientError(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }

        log.debug("Клиент c telegramUserId: {} успешно создан", telegramUserId);
    }
}
