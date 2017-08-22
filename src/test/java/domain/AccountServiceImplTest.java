package domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceImplTest {
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService = new AccountServiceImpl(new InMemoryAccountRepository());
    }

    @Test
    public void should_have_empty_accounts_at_beginning() throws Exception {
        assertThat(accountService.getAccounts()).isEmpty();
    }

    @Test
    public void should_contain_an_account_when_adding_one() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(0).build();
        Account account = Account.AccountBuilder.anAccount().withBalance(balance).build();
        accountService.add(account);
        assertThat(accountService.getAccounts()).containsExactly(account);
    }

    @Test
    public void should_contain_several_accounts_when_adding_several() throws Exception {
        Balance balance1 = Balance.BalanceBuilder.aBalance().withAmount(0).build();
        Account account1 = Account.AccountBuilder.anAccount().withBalance(balance1).build();
        Balance balance2 = Balance.BalanceBuilder.aBalance().withAmount(0).build();
        Account account2 = Account.AccountBuilder.anAccount().withBalance(balance1).build();
        accountService.add(account1);
        accountService.add(account2);
        assertThat(accountService.getAccounts()).containsExactly(account2, account1);
    }

    @Test
    public void should_return_empty_bank_account_when_initialize_account_without_money() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(0).build();
        Account account = Account.AccountBuilder.anAccount().withBalance(balance).build();
        accountService.add(account);
        assertThat(accountService.checkAccountBalance(accountService.getAccounts().get(0))).isEqualTo(balance);
    }

    @Test
    public void should_return_bank_account_with_initiating_balance() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withBalance(balance).build();
        accountService.add(account);
        assertThat(accountService.checkAccountBalance(accountService.getAccounts().get(0))).isEqualTo(balance);
    }

    @Test
    public void should_withdraw_and_save_operation() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withBalance(balance).build();
        accountService.add(account);

    }
}