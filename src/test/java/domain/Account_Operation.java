package domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import service.DateService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_Operation {

    private DateService dateService;

    @Before
    public void setup() throws Exception{
        dateService = Mockito.mock(DateService.class);
    }

    @Test
    public void should_save_no_operation_after_zero_operation() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        assertThat(account.listOperations()).isEmpty();
    }

    @Test
    public void should_contain_one_operation_after_operation_deposit() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.depose(10.0);
        assertThat(account.listOperations().size()).isEqualTo(1);
    }

    @Test
    public void should_contain_one_operation_after_operation_withdrawal() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);
        assertThat(account.listOperations().size()).isEqualTo(1);
    }

    @Test
    public void should_contain_list_of_all_operations_done() throws Exception {
        Mockito.when(dateService.getDate()).thenReturn(LocalDate.of(2017, 6, 23), LocalDate.of(2017, 12, 4));

        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);
        account.depose(10.0);

        Operation operation1 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(LocalDate.of(2017, 6, 23))
                .withType("WITHDRAWAL")
                .build();
        Operation operation2 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(LocalDate.of(2017, 12, 4))
                .withType("DEPOSIT")
                .build();
        assertThat(account.listOperations().size()).isEqualTo(2);
        assertThat(account.listOperations().get(0)).isEqualTo(operation1);
        assertThat(account.listOperations().get(1)).isEqualTo(operation2);
    }

}
