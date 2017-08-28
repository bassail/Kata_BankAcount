package domain;

import domain.exceptions.IllegalNegativeAmountException;
import org.junit.Before;
import org.junit.Test;
import service.DateService;
import service.DateServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class Account_consult_deposeTest {

    private DateService dateService;

    @Before
    public void setup() throws Exception{
        dateService = DateServiceImpl.getInstance();
    }

    @Test
    public void should_return_empty_bank_account_when_initialize_account_without_money() throws Exception{
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(0).build();
        assertThat(account.getAccountBalance()).
                isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_bank_balance_when_initialize_account_with_positive_balance() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_same_balance_when_depose_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.depose(0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_increased_balance_when_deposing_different_than_zero() throws Exception{
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.depose(100.0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(110.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test(expected = IllegalNegativeAmountException.class)
    public void should_return_same_balance_when_deposing_negative_amount() throws Exception{
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.depose(-5.0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.getAccountBalance())
                .isEqualTo(expectedBalance);
    }
}
