package domain;

import domain.exceptions.IllegalNegativeAmountException;
import org.junit.Before;
import org.junit.Test;
import service.DateService;
import service.DateServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_withdrawTest {
    private DateService dateService;

    @Before
    public void setup() throws Exception{
        dateService = DateServiceImpl.getInstance();
    }

    @Test
    public void should_return_decreased_balance_after_withdrawing_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_decreased_balance_after_withdraw_different_than_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(5.0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(5.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test (expected = IllegalNegativeAmountException.class)
    public void should_return_same_balance_when_withdrawing_negative_amount() throws Exception{
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(-5.0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

}
