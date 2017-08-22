package domain;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private List<Operation> operations;

    private Balance currentBalance;

    Balance checkAccountBalance() {
        return this.currentBalance;
    }

    List<Operation> listOperations(){
        return this.operations;
    }

    double deposeAndSaveOperation(double amount){
        return this.currentBalance.depose(amount);
    }

    double withdrawAndSaveOperation(double amount) {
        if(amount > this.checkAccountBalance().amount){
            throw new IllegalStateException();
        }
        return this.currentBalance.withdraw(amount);
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
