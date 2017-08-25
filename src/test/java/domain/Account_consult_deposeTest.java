package domain;

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
        assertThat(account.checkAccountBalance()).
                isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_bank_balance_when_initialize_account_with_something_different_than_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_same_balance_when_depose_zero() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.deposeAndSaveOperation(0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }

    @Test
    public void should_return_increased_balance_when_deposing_different_than_zero() throws Exception{
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.deposeAndSaveOperation(100.0);
        Balance expectedBalance = Balance.BalanceBuilder.aBalance().withAmount(110.0).build();
        assertThat(account.checkAccountBalance())
                .isEqualTo(expectedBalance);
    }
}
