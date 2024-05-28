package ru.gpbitfactory.minibank.middle.restclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.api.UsersApiClient;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseInner;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequest;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.backend.dto.UserResponse;
import ru.gpbitfactory.minibank.middle.restclient.mock.BackendUserMockService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersApiClientMock implements UsersApiClient {

    @Override
    public ResponseEntity<Void> createUser(CreateUserRequest createUserRequest) {
        BackendUserMockService.createUser(createUserRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> createUserAccount(Long id, CreateAccountRequest createAccountRequest) {
        // TODO https://github.com/gpb-it-factory/gladskoy-middle-service/issues/11
        return null;
    }

    @Override
    public ResponseEntity<List<AccountsListResponseInner>> getUserAccounts(Long id) {
        // TODO https://github.com/gpb-it-factory/gladskoy-middle-service/issues/12
        return null;
    }

    @Override
    public ResponseEntity<UserResponse> getUserByTelegramId(Long id) {
        // TODO https://github.com/gpb-it-factory/gladskoy-middle-service/issues/13
        return null;
    }
}
