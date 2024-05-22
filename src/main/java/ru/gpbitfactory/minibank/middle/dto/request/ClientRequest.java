package ru.gpbitfactory.minibank.middle.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotBlank(message = "не может быть пустым")
    @Pattern(regexp = "[^а-яА-Я]*", message = "не может содержать кириллицу")
    private String username;
}
