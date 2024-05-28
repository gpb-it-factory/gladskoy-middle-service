package ru.gpbitfactory.minibank.middle.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.dto.mapper.ClientMapper;
import ru.gpbitfactory.minibank.middle.dto.request.CreateClientRequest;
import ru.gpbitfactory.minibank.middle.dto.response.CreateClientResponse;
import ru.gpbitfactory.minibank.middle.exception.CreateClientError;
import ru.gpbitfactory.minibank.middle.service.ClientsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final ClientsService clientsService;
    private final ClientMapper clientMapper;

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
