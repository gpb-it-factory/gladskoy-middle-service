package ru.gpbitfactory.minibank.middle.createaccount.restclient;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequestV2;
import ru.gpbitfactory.minibank.backend.dto.UserResponseV2;

@FeignClient(name = "backend-users-accounts-service", url = "${backend.service.url}")
public interface BackendServiceUsersApiClient {

    @GetMapping("/v2/users/{id}")
    ResponseEntity<UserResponseV2> getUserByTelegramId(@PathVariable long id);

    @PostMapping("/v2/users/{id}/accounts")
    ResponseEntity<Void> createUserAccount(@PathVariable long id, @RequestBody @Valid CreateAccountRequestV2 createAccountRequest);
}
