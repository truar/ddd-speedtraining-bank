package com.example.bankdddspeedtraining.application;

import com.example.bankdddspeedtraining.domain.account.Account;
import com.example.bankdddspeedtraining.domain.account.AccountRepository;
import com.example.bankdddspeedtraining.domain.account.DepositDone;
import com.example.bankdddspeedtraining.domain.account.UnauthorizedDepositException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class AccountApplicationService {

    private AccountRepository accountRepository;
    private EventPublisher eventPublisher;

    public void openAccount(String clientId) {
        String accountId = UUID.randomUUID().toString();
        Account account = new Account(accountId, clientId);
        accountRepository.save(account);
    }

    @Transactional
    public void depositMoneyIn(String accountId, int amount) throws UnauthorizedDepositException {
        Account account = accountRepository.findById(accountId);
//        bdfGateway.canDeposit();
        // authenticatedUser
        DepositDone event = account.deposit(amount);
        accountRepository.save(account);
        eventPublisher.publish(event);
    }
}
