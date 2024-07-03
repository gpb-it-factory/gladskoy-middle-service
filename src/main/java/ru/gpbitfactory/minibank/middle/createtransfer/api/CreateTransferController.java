package ru.gpbitfactory.minibank.middle.createtransfer.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.createtransfer.dto.CreateTransferRequest;
import ru.gpbitfactory.minibank.middle.createtransfer.dto.CreateTransferResponse;
import ru.gpbitfactory.minibank.middle.createtransfer.dto.TransferMapper;
import ru.gpbitfactory.minibank.middle.createtransfer.exception.CreateTransferRequestValidationError;
import ru.gpbitfactory.minibank.middle.createtransfer.service.CreateTransferService;

/**
 * API для сервиса <a href="https://github.com/gpb-it-factory/gladskoy-telegram-bot">gladskoy-telegram-bot</a>.
 * Предоставляет операции для работы с сущностями клиентов Мини-банка.
 *
 * @since 0.3.1
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/transfers")
public class CreateTransferController {

    private final TransferMapper transferMapper;
    private final CreateTransferService createTransferService;

    /**
     * Создание перевод между счетами клиентов.
     *
     * @return в случае успеха {@link CreateTransferResponse CreateMoneyTransferResponse}
     * и HTTP Status Code 200, иначе {@link ru.gpbitfactory.minibank.middle.createtransfer.dto.ErrorResponse ErrorResponse}
     * и HTTP Status Code 422.
     * @since 0.3.1
     */
    @PostMapping
    public ResponseEntity<CreateTransferResponse> createNewClient(@RequestBody @Valid CreateTransferRequest request,
                                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            final var errorMessage = buildErrorMessage(bindingResult);
            throw new CreateTransferRequestValidationError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }

        final var createTransferRequest = transferMapper.map(request);
        createTransferService.createTransfer(createTransferRequest);

        final var createTransferResponse = CreateTransferResponse.builder()
                .message("Перевод успешно совершён!")
                .build();
        return new ResponseEntity<>(createTransferResponse, HttpStatus.CREATED);
    }

    private String buildErrorMessage(BindingResult bindingResult) {
        final var errorMessage = new StringBuilder();
        bindingResult.getFieldErrors().forEach(error ->
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ")
        );

        return errorMessage.substring(0, errorMessage.length() - 2);
    }
}
