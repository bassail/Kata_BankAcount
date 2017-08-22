package domain;

import java.util.Date;
import java.util.List;

public interface AccountService {
    void add(Account account);
    List<Account> getAccounts();
    double withdrawAndSaveOperation(double amount, Date date, Account account);
    double depositAndSaveOperation(double amount, Date date, Account account);
    Balance checkAccountBalance(Account account);
}
