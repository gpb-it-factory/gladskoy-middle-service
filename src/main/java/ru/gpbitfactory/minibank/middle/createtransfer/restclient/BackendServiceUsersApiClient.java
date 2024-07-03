package ru.gpbitfactory.minibank.middle.createtransfer.restclient;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gpbitfactory.minibank.backend.dto.CreateTransferRequestV2;

@FeignClient(name = "backend-users-create-transfer-service", url = "${backend.service.url}")
public interface BackendServiceUsersApiClient {

    @PostMapping("/v2/transfers")
    ResponseEntity<Void> createTransfer(@RequestBody @Valid CreateTransferRequestV2 createTransferRequest);
}
