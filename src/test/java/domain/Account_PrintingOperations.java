package domain;

import org.junit.Before;
import org.junit.Test;
import service.DateService;
import service.DateServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_PrintingOperations {
    PrintStream printer;
    private DateService dateService;

    @Before
    public void setup() {
        dateService = DateServiceImpl.getInstance();
        printer = System.out;
    }

    @Test
    public void should_print_no_operations_when_no_operation_in_account() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(printer);
        account.printOperations(printer);
        String output = new String(byteArrayOutputStream.toByteArray());
        assertThat(output).isEqualTo("");
    }

    @Test
    public void should_print_no_operation_when_withdrawing_more_than_actual_balance() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrinter = new PrintStream(byteArrayOutputStream);
        System.setOut(newPrinter);
        account.printOperations(newPrinter);
        System.setOut(printer);
        String output = new String(byteArrayOutputStream.toByteArray());
        assertThat(output).isEqualTo("");
    }

    @Test
    public void should_print_one_withdrawal_operation_after_one_valid_withdrawal() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(50.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrinter = new PrintStream(byteArrayOutputStream);
        System.setOut(newPrinter);
        account.printOperations(newPrinter);
        System.setOut(printer);
        String output = new String(byteArrayOutputStream.toByteArray());
        assertThat(output).contains("WITHDRAWAL | " + LocalDate.now() + " | 10.0 | 40.0");
    }

    @Test
    public void should_print_withdrawals_and_deposits_operations_according_to_hystory() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().withAmount(100.0).build();
        Account account = Account.AccountBuilder.anAccount(dateService).withCurrentBalance(balance).build();
        account.withdraw(10.0);
        account.depose(50.0);
        account.withdraw(20.0);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream newPrinter = new PrintStream(byteArrayOutputStream);
        System.setOut(newPrinter);
        account.printOperations(newPrinter);
        System.setOut(printer);
        String output = new String(byteArrayOutputStream.toByteArray());
        assertThat(output).contains("WITHDRAWAL | " + LocalDate.now() + " | 10.0 | 90.0");
        assertThat(output).contains("DEPOSIT | "    + LocalDate.now() + " | 50.0 | 140.0");
        assertThat(output).contains("WITHDRAWAL | " + LocalDate.now() + " | 20.0 | 120.0");
    }
}
