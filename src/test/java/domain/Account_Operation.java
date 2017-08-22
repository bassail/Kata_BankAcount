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
    public void should_save_operation_after_operation() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10).build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        account.deposeAndSaveOperation(10, LocalDate.now());
        assertThat(account.listOperations().size()).isEqualTo(1);
    }
}
