package ru.gpbitfactory.minibank.middle.mockbackend.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequestV2;
import ru.gpbitfactory.minibank.middle.createclient.restclient.BackendServiceUsersApiClient;
import ru.gpbitfactory.minibank.middle.mockbackend.domain.MockUser;
import ru.gpbitfactory.minibank.middle.mockbackend.service.UserMockService;

@Component
@RequiredArgsConstructor
public class UsersMockApiClient implements BackendServiceUsersApiClient {

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
    public ResponseEntity<Void> createUser(CreateUserRequestV2 createUserRequest) {
        var mockUser = MockUser.builder()
                .telegramUserId(createUserRequest.getUserId())
                .telegramUserName(createUserRequest.getUserName())
                .build();
        userMockService.createUser(mockUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
