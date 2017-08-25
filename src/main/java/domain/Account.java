package domain;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String DEPOSIT = "DEPOSIT";
    private final String WITHDRAWAL = "WITHDRAWAL";
    private List<Operation> operations;

    private Balance currentBalance;


    Balance checkAccountBalance() {
        return this.currentBalance;
    }

    List<Operation> listOperations(){
        return this.operations;
    }

    double deposeAndSaveOperation(double amount){
        double newBalance = this.currentBalance.depose(amount);
        this.createNewOperation(amount, DEPOSIT);
        return newBalance;
    }

    private void createNewOperation(double amount, String type) {
       Operation operation = Operation.OperationBuilder.anOperation().withAmount(amount).withBalanceAmount(this.currentBalance.amount).withDate(LocalDate.now()).withType(type).build();
        this.operations.add(operation);
    }

    double withdrawAndSaveOperation(double amount) {
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

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withCurrentBalance(Balance currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.operations = new ArrayList<Operation>();
            account.currentBalance = this.currentBalance;
            return account;
        }
    }
}
