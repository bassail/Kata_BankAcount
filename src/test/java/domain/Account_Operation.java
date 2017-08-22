package domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_Operation {

    @Test
    public void should_save_no_operation_after_zero_operation() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        assertThat(account.listOperations()).isEmpty();
    }
}
