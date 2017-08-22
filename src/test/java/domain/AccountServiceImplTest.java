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
}
