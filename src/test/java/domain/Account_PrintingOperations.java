package domain;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class Account_PrintingOperations {
    PrintStream printer;

    @Before
    public void setup(){
        printer = System.out;
    }

    @Test
    public void should_print_no_operations_when_no_operation_in_account() throws Exception {
        Balance balance = Balance.BalanceBuilder.aBalance().build();
        Account account = Account.AccountBuilder.anAccount().withCurrentBalance(balance).build();
        //TODO HOW CHECK WHAT IS PRINTED ? MOCKITO ?
        //assertThat(account.printOperations(printer)).isEqualTo;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(printer);
        account.printOperations(printer);
        String output = new String(byteArrayOutputStream.toByteArray());
        assertThat(output).isEqualTo("");
    }


}
