package ru.gpbitfactory.minibank.middle.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpbitfactory.minibank.middle.dto.mapper.ClientMapper;
import ru.gpbitfactory.minibank.middle.dto.request.ClientRequest;
import ru.gpbitfactory.minibank.middle.dto.response.ClientBalanceResponse;
import ru.gpbitfactory.minibank.middle.dto.response.ClientResponse;
import ru.gpbitfactory.minibank.middle.exception.CreateClientException;
import ru.gpbitfactory.minibank.middle.service.ClientsService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientsController {

    private final ClientsService clientsService;
    private final ClientMapper mapper;

    @PostMapping
    public ClientResponse createNewClient(@RequestBody @Valid ClientRequest clientRequest, BindingResult bindingResult) {
        log.debug("Поступил запрос на создание клиента c username: {}", clientRequest.getUsername());
        if (bindingResult.hasErrors()) {
            var errorMessage = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                log.warn("Запрос не валиден: {}", error.getDefaultMessage());
                errorMessage.append(error.getField()).append(" - ").append(error.getDefaultMessage());
            });
            throw new CreateClientException(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage.toString());
        }

        final var clientToSave = mapper.toClient(clientRequest);
        final var savedClient = clientsService.save(clientToSave);
        log.debug("Клиент с username {} успешно создан", clientRequest.getUsername());

        return mapper.toClientResponse(savedClient);
    }

    @GetMapping("/{id}/balance")
    public ClientBalanceResponse getCurrentBalance(@PathVariable("id") long id) {
        log.debug("Поступил запрос на получение текущего баланса клиента c id {}", id);
        var balance = clientsService.getById(id);
        log.debug("Запрос на получение текущего баланса клиента {} выполнен успешно", id);

        return mapper.toClientBalanceResponse(balance);
    }
}
