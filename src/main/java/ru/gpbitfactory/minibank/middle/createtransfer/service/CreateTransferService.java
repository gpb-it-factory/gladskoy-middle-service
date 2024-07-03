package ru.gpbitfactory.minibank.middle.createtransfer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.backend.dto.CreateTransferRequestV2;
import ru.gpbitfactory.minibank.backend.dto.ErrorV2;
import ru.gpbitfactory.minibank.middle.createtransfer.exception.CreateTransferError;
import ru.gpbitfactory.minibank.middle.createtransfer.restclient.BackendServiceUsersApiClient;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTransferService {

    private final BackendServiceUsersApiClient usersApiClient;
    private final ObjectMapper mapper;

    public void createTransfer(CreateTransferRequestV2 createTransferRequest) {
        var from = createTransferRequest.getFrom();
        var to = createTransferRequest.getTo();
        log.info("Поступил запрос на перевод средств от клиента {} клиенту {}", from, to);

        try {
            usersApiClient.createTransfer(createTransferRequest);
        } catch (FeignException e) {
            log.error("В процессе перевода возникала непредвиденная ситуация", e);
            var errorMessage = String.format("Не удалось осуществить перевод средств от клиента %s клиенту %s: %s",
                    from, to, getBackendMessage(e));
            throw new CreateTransferError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }

        log.info("Перевод средств от клиента {} клиенту {} успешно совершён", from, to);
    }

    private String getBackendMessage(FeignException e) {
        try {
            var error = mapper.readValue(e.contentUTF8(), ErrorV2.class);
            return error.getMessage().toLowerCase();
        } catch (IOException ex) {
            log.error("Не удалось десериализовать сообщение", ex);
        }
        return "";
    }
}
