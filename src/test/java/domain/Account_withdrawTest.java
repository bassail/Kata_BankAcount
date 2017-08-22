package domain;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_withdrawTest {

    @Test
    public void should_return_decreased_balance_after_withdrawing_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(0, LocalDate.now());
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_decreased_balance_after_withdraw_different_than_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(5, LocalDate.now());
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(5).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test(expected = IllegalStateException.class)
    public void expects_exception_when_withdraw_more_than_balance() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(20, LocalDate.now());
    }
}
