package ru.gpbitfactory.minibank.middle.createclient.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.createclient.dto.ClientMapper;
import ru.gpbitfactory.minibank.middle.createclient.dto.CreateClientRequest;
import ru.gpbitfactory.minibank.middle.createclient.dto.CreateClientResponse;
import ru.gpbitfactory.minibank.middle.createclient.exception.CreateClientError;
import ru.gpbitfactory.minibank.middle.createclient.service.ClientsService;

/**
 * API для сервиса <a href="https://github.com/gpb-it-factory/gladskoy-telegram-bot">gladskoy-telegram-bot</a>.
 * Предоставляет операции для работы с сущностями клиентов Мини-банка:
 * <ul>
 *     <li>Создание нового клиента - {@link #createNewClient(CreateClientRequest, BindingResult)}</li>
 *     <li>Получение данных клиента - TBD</li>
 *     <li>Открытие счёта в Мини-банке - TBD</li>
 *     <li>Получение счетов клиента - TBD</li>
 * </ul>
 *
 * @since 0.0.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final ClientsService clientsService;
    private final ClientMapper clientMapper;

    /**
     * Создание нового клиента
     *
     * @param createClientRequest запрос на создание нового клиента.
     * @param bindingResult       контейнер для ошибок, которые могут возникнуть в результате валидации запроса на создание нового клиента.
     * @return в случае успеха HTTP Status Code 200 и сообщение об успешном создании клиента, иначе HTTP Status Code 422 и ответ с ошибкой.
     * @see CreateClientRequest
     * @see BindingResult
     * @see CreateClientResponse
     * @see ru.gpbitfactory.minibank.middle.createclient.dto.ErrorResponse ErrorResponse
     * @since 0.0.1
     */
    @PostMapping
    public ResponseEntity<CreateClientResponse> createNewClient(@RequestBody @Valid CreateClientRequest createClientRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final var errorMessage = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error ->
                    errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage())
            );
            throw new CreateClientError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage.toString());
        }

        final var createUserRequest = clientMapper.map(createClientRequest);
        clientsService.createClient(createUserRequest);

        final var createClientResponse = CreateClientResponse.builder()
                .message("Клиент успешно зарегистрирован!")
                .build();
        return new ResponseEntity<>(createClientResponse, HttpStatus.OK);
    }
}
