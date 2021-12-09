package com.example.bankdddspeedtraining.domain.account;

public interface AccountRepository {

    void save(Account account);

    Account findById(String accountId);
}
