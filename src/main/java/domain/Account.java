package domain;
import java.util.List;

public class Account {

    private List<Operation> operations;

    private Balance currentBalance;


    Balance checkAccountBalance() {
        return this.currentBalance;
    }

    double depose(double amount){
        return this.currentBalance.depose(amount);
    }

    double withdraw(double amount) {
        return this.currentBalance.withdraw(amount);
    }

    public static final class AccountBuilder {
        private Balance balance;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withBalance(Balance balance) {
            this.balance = balance;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.currentBalance = balance;
            return account;
        }
    }
}
