package ru.gpbitfactory.minibank.middle.restclient.mock;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import ru.gpbitfactory.minibank.backend.dto.CreateUserRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@UtilityClass
public class BackendUserMockService {

    private final List<BackendUserMockDto> userRegistry = new ArrayList<>();
    private long userIdSequence;

    private long getNextId() {
        return ++userIdSequence;
    }

    public void createUser(CreateUserRequest createUserRequest) {
        var telegramUserId = createUserRequest.getUserId();
        var client = userRegistry.stream()
                .filter(c -> c.telegramUserId().equals(telegramUserId))
                .findFirst();

        if (client.isPresent()) {
            var errorMessage = String.format("Пользователь с telegramUserId: %s уже зарегистрирован", telegramUserId);
            throw new CreateBackendUserError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }

        var newUser = BackendUserMockDto.builder()
                .id(getNextId())
                .telegramUserId(telegramUserId)
                .build();
        userRegistry.add(newUser);
    }
}
