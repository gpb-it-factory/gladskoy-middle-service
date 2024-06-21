package ru.gpbitfactory.minibank.middle.createclient.restclient;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequestV2;

@FeignClient(name = "backend-users-service", url = "${backend.service.url}")
public interface BackendServiceUsersApiClient {

    @PostMapping("/users")
    ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest createUserRequest);

    @PostMapping("/v2/users")
    ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestV2 createUserRequest);
}
