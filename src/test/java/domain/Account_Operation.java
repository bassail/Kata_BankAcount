package domain;

import org.junit.Before;
import org.junit.Test;
import service.DateService;
import service.DateServiceImpl;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_Operation {

    private DateService dateService;

    @Before
    public void setup() throws Exception{
        dateService = DateServiceImpl.getInstance();
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
        account.deposeAndSaveOperation(10.0);
        assertThat(account.listOperations().size()).isEqualTo(1);
    }

    @Test
    public void should_contain_one_operation_after_operation_withdrawal() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(10.0);
        assertThat(account.listOperations().size()).isEqualTo(1);
    }

    @Test
    public void should_contain_list_of_all_operations_done() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(10.0);
        account.deposeAndSaveOperation(10.0);


        Operation operation1 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(LocalDate.now())
                .withType("WITHDRAWAL")
                .build();
        Operation operation2 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(LocalDate.now())
                .withType("DEPOSIT")
                .build();
        assertThat(account.listOperations().size()).isEqualTo(2);
        assertThat(account.listOperations().get(0)).isEqualTo(operation1);
        assertThat(account.listOperations().get(1)).isEqualTo(operation2);
    }

    @Test
    public void should_contain_list_of_all_operations_done_dates() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(10.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdrawAndSaveOperation(10.0);
        account.deposeAndSaveOperation(10.0);


        Operation operation1 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(dateService.getDate())
                .withType("WITHDRAWAL")
                .build();
        Operation operation2 = Operation.OperationBuilder.anOperation()
                .withAmount(10.0)
                .withDate(dateService.getDate())
                .withType("DEPOSIT")
                .build();
        assertThat(account.listOperations().size()).isEqualTo(2);
        assertThat(account.listOperations().get(0)).isEqualTo(operation1);
        assertThat(account.listOperations().get(1)).isEqualTo(operation2);
    }


}
