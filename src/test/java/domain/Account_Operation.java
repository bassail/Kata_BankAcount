package domain;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_Operation {

    @Test
    public void should_save_no_operation_after_zero_operation() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        assertThat(account.listOperations()).isEmpty();
    }

    @Test
    public void should_contain_one_operation_after_operation_deposit() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.deposeAndSaveOperation(10, LocalDate.now());
        assertThat(account.listOperations().size()).isEqualTo(1);
    }

    @Test
    public void should_contain_one_operation_after_operation_withdrawal() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(10, LocalDate.now());
        assertThat(account.listOperations().size()).isEqualTo(1);
    }


}
