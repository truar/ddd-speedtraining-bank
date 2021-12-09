package com.example.bankdddspeedtraining.domain.account;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountId;
    private final String clientId;
    private boolean enabled;
    private List<Integer> transactions;

    public Account(String accountId, String clientId) {
        this.accountId = accountId;
        this.clientId = clientId;
        this.enabled = false;
        this.transactions = new ArrayList<>();
    }

    public DepositDone deposit(int amount) throws UnauthorizedDepositException {
        if (isEnabled()) {
            transactions.add(amount);
        } else if(transactions.isEmpty()) {
            transactions.add(amount);
            enabled = true;
        } else {
            throw new UnauthorizedDepositException();
        }
        return new DepositDone(/*...*/);
    }

    public String getClientId() {
        return clientId;
    }

    public int getBalance() {
        return transactions.stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void disable() {
        this.enabled = false;
    }
}
