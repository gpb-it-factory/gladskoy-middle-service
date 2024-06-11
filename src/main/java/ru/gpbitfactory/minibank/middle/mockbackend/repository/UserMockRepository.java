package ru.gpbitfactory.minibank.middle.mockbackend.repository;

import org.springframework.stereotype.Repository;
import ru.gpbitfactory.minibank.middle.mockbackend.domain.MockUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserMockRepository {

    private final List<MockUser> userRegistry = new ArrayList<>();
    private long userIdSequence;

    private long getNextId() {
        return ++userIdSequence;
    }

    public void save(MockUser entity) {
        var user = MockUser.builder()
                .id(getNextId())
                .telegramUserId(entity.getTelegramUserId())
                .accounts(entity.getAccounts())
                .build();
        userRegistry.add(user);
    }

    public Optional<MockUser> findByTelegramUserId(long id) {
        return userRegistry.stream()
                .filter(c -> c.getTelegramUserId().equals(id))
                .findFirst();
    }
}
