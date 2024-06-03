package ru.gpbitfactory.minibank.middle.mockbackend.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MockUser {

    @NotNull
    private Long id;

    @NotNull
    private Long telegramUserId;

    private List<MockUserAccount> accounts;

    public void setAccount(MockUserAccount account) {
        if (this.accounts == null) {
            this.accounts = new ArrayList<>();
        }
        this.accounts.add(account);
    }
}