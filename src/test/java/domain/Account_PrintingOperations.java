package domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.DateService;
import service.DateServiceImpl;
import service.PrinterService;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class Account_PrintingOperations {
    @Mock
    PrinterService printer;
    private DateService dateService;

    @Before
    public void setup() {
        dateService = DateServiceImpl.getInstance();
    }

    @Test
    public void should_print_no_operations_when_no_operation_in_account() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.printOperations(printer);
        verifyZeroInteractions(printer);
    }

    @Test
    public void should_print_no_operation_when_withdrawing_more_than_actual_balance() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);

        account.printOperations(printer);
        verifyZeroInteractions(printer);
    }

    @Test
    public void should_print_one_withdrawal_operation_after_one_valid_withdrawal() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(50.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);

        account.printOperations(printer);
        verify(printer).print("WITHDRAWAL | " + LocalDate.now() + " | 10.0 | 40.0");
    }

    @Test
    public void should_print_withdrawals_and_deposits_operations_according_to_hystory() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(100.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);
        account.depose(50.0);
        account.withdraw(20.0);


        account.printOperations(printer);
        verify(printer).print("WITHDRAWAL | " + LocalDate.now() + " | 10.0 | 90.0");
        verify(printer).print("DEPOSIT | "    + LocalDate.now() + " | 50.0 | 140.0");
        verify(printer).print("WITHDRAWAL | " + LocalDate.now() + " | 20.0 | 120.0");
    }
}
