package domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_withdrawTest {

    @Test
    public void should_return_decreased_balance_after_withdrawing_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withBalance(balance).build();
        account.withdraw(0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }
}
