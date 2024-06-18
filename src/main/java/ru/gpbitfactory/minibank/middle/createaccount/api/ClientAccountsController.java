package ru.gpbitfactory.minibank.middle.createaccount.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.createaccount.dto.AccountMapper;
import ru.gpbitfactory.minibank.middle.createaccount.dto.CreateClientAccountRequest;
import ru.gpbitfactory.minibank.middle.createaccount.dto.CreateClientAccountResponse;
import ru.gpbitfactory.minibank.middle.createaccount.exception.CreateClientAccountError;
import ru.gpbitfactory.minibank.middle.createaccount.service.ClientAccountsService;

/**
 * API для сервиса <a href="https://github.com/gpb-it-factory/gladskoy-telegram-bot">gladskoy-telegram-bot</a>.
 * Предоставляет операции для работы с сущностями клиентов Мини-банка.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientAccountsController {

    private final ClientAccountsService accountsService;
    private final AccountMapper accountsMapper;

    /**
     * Создание нового счёта для клиента.
     *
     * @param telegramId    идентификатор клиента в Telegram.
     * @param request       запрос на открытие счёта для клиента.
     * @param bindingResult контейнер для ошибок, которые могут возникнуть в результате валидации запроса на открытие нового счёта.
     * @return в случае успеха HTTP Status Code 200 и сообщение об успешном открытии счёта, иначе HTTP Status Code 422 и ответ с ошибкой.
     * @see CreateClientAccountRequest
     * @see BindingResult
     * @see CreateClientAccountResponse
     * @see ru.gpbitfactory.minibank.middle.createaccount.dto.ErrorResponse ErrorResponse
     * @since 0.1.0
     */
    @PostMapping("/{telegramId}/accounts")
    public ResponseEntity<CreateClientAccountResponse> createClientAccount(@PathVariable long telegramId,
                                                                           @RequestBody @Validated CreateClientAccountRequest request,
                                                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final var errorMessage = buildErrorMessage(bindingResult);
            throw new CreateClientAccountError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }

        final var createAccountRequest = accountsMapper.map(request);
        accountsService.createUserAccount(telegramId, createAccountRequest);

        final var createClientResponse = CreateClientAccountResponse.builder()
                .message("Счёт успешно открыт!")
                .build();
        return new ResponseEntity<>(createClientResponse, HttpStatus.CREATED);
    }

    private String buildErrorMessage(BindingResult bindingResult) {
        final var errorMessage = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );

        return errorMessage.substring(0, errorMessage.length() - 2);
    }
}
