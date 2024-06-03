package ru.gpbitfactory.minibank.middle.mockbackend.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MockUserAccount {

    @NotNull
    private UUID id;

    @Setter
    private String name;

    @Setter
    private BigDecimal amount;
}
