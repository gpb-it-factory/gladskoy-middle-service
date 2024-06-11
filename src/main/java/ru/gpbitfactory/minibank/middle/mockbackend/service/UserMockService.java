package ru.gpbitfactory.minibank.middle.mockbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.gpbitfactory.minibank.middle.mockbackend.domain.MockUser;
import ru.gpbitfactory.minibank.middle.mockbackend.exception.CreateMockUserError;
import ru.gpbitfactory.minibank.middle.mockbackend.repository.UserMockRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMockService {

    private final UserMockRepository userMockRepository;

    public void createUser(MockUser mockUser) {
        var telegramUserId = mockUser.getTelegramUserId();
        var user = userMockRepository.findByTelegramUserId(telegramUserId);
        if (user.isPresent()) {
            var errorMessage = String.format("Пользователь с telegramUserId: %s уже зарегистрирован", telegramUserId);
            throw new CreateMockUserError(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
        }
        userMockRepository.save(mockUser);
    }
}
