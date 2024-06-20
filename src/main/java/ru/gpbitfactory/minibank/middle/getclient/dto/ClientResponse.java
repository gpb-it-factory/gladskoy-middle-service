package ru.gpbitfactory.minibank.middle.getclient.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Данные клиента
 */
@Setter
@Getter
public class ClientResponse {

    private List<ClientAccount> accounts;

    public ClientResponse() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(ClientAccount account) {
        if (accounts == null) {
            accounts = new ArrayList<>();
        }
        accounts.add(account);
    }
}
