package ru.gpbitfactory.minibank.middle.getclient.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.getclient.dto.ClientAccountMapper;
import ru.gpbitfactory.minibank.middle.getclient.dto.ClientResponse;
import ru.gpbitfactory.minibank.middle.getclient.service.GetClientService;

/**
 * API для сервиса <a href="https://github.com/gpb-it-factory/gladskoy-telegram-bot">gladskoy-telegram-bot</a>.
 * Предоставляет операции для работы с сущностями клиентов Мини-банка.
 *
 * @since 0.2.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class GetClientController {

    private final GetClientService clientsService;
    private final ClientAccountMapper clientAccountMapper;

    /**
     * Получение данных клиента.
     *
     * @return в случае успеха {@link ru.gpbitfactory.minibank.middle.getclient.dto.ClientResponse ClientResponse}
     * HTTP Status Code 200, иначе {@link ru.gpbitfactory.minibank.middle.getclient.dto.ErrorResponse ErrorResponse}
     * и HTTP Status Code 422.
     * @since 0.2.1
     */
    @GetMapping("/{telegramId}")
    public ResponseEntity<ClientResponse> getClient(@PathVariable long telegramId) {
        final var clientResponse = new ClientResponse();
        clientsService.getClientAccounts(telegramId).stream()
                .map(clientAccountMapper::map)
                .forEach(clientResponse::addAccount);

        return new ResponseEntity<>(clientResponse, HttpStatus.OK);
    }
}
