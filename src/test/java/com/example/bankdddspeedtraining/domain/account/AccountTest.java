package com.example.bankdddspeedtraining.domain.account;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountTest {

    @Test
    void can_open_an_account() {
        String clientId = "clientId";

        Account account = new Account("accountId", clientId);

        assertThat(account.getClientId()).isEqualTo(clientId);
        assertThat(account.getBalance()).isEqualTo(0);
        assertThat(account.isEnabled()).isFalse();
    }

    @Test
    void deposit_money_on_account() throws UnauthorizedDepositException {
        String clientId = "clientId";
        Account account = new Account("accountId", clientId);

        account.deposit(10);

        assertThat(account.getBalance()).isEqualTo(10);
    }

    @Test
    void first_deposit_money_enables_account() throws UnauthorizedDepositException {
        String clientId = "clientId";
        Account account = new Account("accountId", clientId);

        account.deposit(10);

        assertThat(account.isEnabled()).isEqualTo(true);
    }

    @Test
    void disables_an_account() throws UnauthorizedDepositException {
        String clientId = "clientId";
        Account account = new Account("accountId", clientId);
        account.deposit(10);

        account.disable();

        assertThat(account.isEnabled()).isEqualTo(false);
    }

    @Test
    void can_not_deposit_money_on_a_disabled_account() throws UnauthorizedDepositException {
        String clientId = "clientId";
        Account account = new Account("accountId", clientId);
        account.deposit(10);
        account.disable();

        assertThatThrownBy(() -> account.deposit(10))
                .isInstanceOf(UnauthorizedDepositException.class);
    }
}
