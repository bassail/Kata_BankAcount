package domain;

import java.util.List;

public interface AccountService {
    void add(Account account);
    List<Account> getAccounts();
}
