package ru.gpbitfactory.minibank.middle.getclient.restclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseV2Inner;

import java.util.List;

@FeignClient(name = "backend-users-get-accounts-service", url = "${backend.service.url}")
public interface BackendServiceUsersApiClient {

    @GetMapping("/v2/users/{id}/accounts")
    public ResponseEntity<List<AccountsListResponseV2Inner>> getUserAccounts(@PathVariable long id);
}
