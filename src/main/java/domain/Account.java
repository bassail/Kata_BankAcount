package domain;

import domain.exceptions.IllegalNegativeAmountException;
import service.DateService;
import service.PrinterService;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Operation> operations;
    private Balance currentBalance;
    private DateService dateService;


    Balance getAccountBalance() {
        return this.currentBalance;
    }

    List<Operation> listOperations() {
        return this.operations;
    }

    double depose(double amount) throws IllegalNegativeAmountException {
        if (amount >= 0) {
            double newBalance = this.currentBalance.depose(amount);
            this.createNewOperation(amount, OperationType.DEPOSIT.name());
            return newBalance;
        }else{
            throw new IllegalNegativeAmountException();
        }
    }

    private void createNewOperation(double amount, String type) {
        Operation operation = Operation.OperationBuilder.anOperation().withAmount(amount).withBalanceAmount(this.currentBalance.amount).withDate(dateService.getDate()).withType(type).build();
        this.operations.add(operation);
    }

    double withdraw(double amount) throws IllegalNegativeAmountException {
        if (amount >= 0) {
            if (amount > this.getAccountBalance().amount) {
                return this.currentBalance.amount;
            }
            double newBalance = this.currentBalance.withdraw(amount);
            this.createNewOperation(amount, OperationType.WITHDRAWAL.name());
            return newBalance;
        }else{
            throw new IllegalNegativeAmountException();
        }
    }

    public void printOperations(PrinterService printer) {
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
