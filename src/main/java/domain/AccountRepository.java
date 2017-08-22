package domain;

import java.util.Set;

public interface AccountRepository {

    Set<Account> findAll();

    void save(Account account);
}
