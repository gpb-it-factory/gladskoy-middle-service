package ru.gpbitfactory.minibank.middle.mockbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.api.UsersApiClient;
import ru.gpbitfactory.minibank.backend.dto.AccountsListResponseInner;
import ru.gpbitfactory.minibank.backend.dto.CreateAccountRequest;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.backend.dto.UserResponse;
import ru.gpbitfactory.minibank.middle.mockbackend.domain.MockUser;
import ru.gpbitfactory.minibank.middle.mockbackend.service.UserMockService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UsersMockApiClient implements UsersApiClient {

    private final UserMockService userMockService;

    @Override
    public ResponseEntity<Void> createUser(CreateUserRequest createUserRequest) {
        var mockUser = MockUser.builder()
                .telegramUserId(createUserRequest.getUserId())
                .build();
        userMockService.createUser(mockUser);
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
