package domain;
import service.DateService;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String DEPOSIT = "DEPOSIT";
    private final String WITHDRAWAL = "WITHDRAWAL";
    private List<Operation> operations;


    private Balance currentBalance;
    private DateService dateService;


    Balance checkAccountBalance() {
        return this.currentBalance;
    }

    List<Operation> listOperations(){
        return this.operations;
    }

    double depose(double amount){
        double newBalance = this.currentBalance.depose(amount);
        this.createNewOperation(amount, DEPOSIT);
        return newBalance;
    }

    private void createNewOperation(double amount, String type) {
       Operation operation = Operation.OperationBuilder.anOperation().withAmount(amount).withBalanceAmount(this.currentBalance.amount).withDate(dateService.getDate()).withType(type).build();
       this.operations.add(operation);
    }

    double withdraw(double amount) {
        if(amount > this.checkAccountBalance().amount){
            return this.currentBalance.amount;
        }
        double newBalance = this.currentBalance.withdraw(amount);
        this.createNewOperation(amount, WITHDRAWAL);
        return newBalance;
    }

    public void printOperations(PrintStream printer){
        for (Operation operation : operations) {
            operation.print(printer);
        }
    }


    public static final class AccountBuilder {
        private List<Operation> operations;
        private Balance currentBalance;
        private DateService dateService;

        private AccountBuilder(DateService dateService) {
            this.dateService = dateService;
        }

        public static AccountBuilder anAccount(DateService dateService) {
            return new AccountBuilder(dateService);
        }

        public AccountBuilder withCurrentBalance(Balance currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.operations = new ArrayList<>();
            account.currentBalance = this.currentBalance;
            account.dateService = this.dateService;
            return account;
        }
    }
}
