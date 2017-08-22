package domain;

import java.util.HashSet;
import java.util.Set;

public class InMemoryAccountRepository implements AccountRepository{

    private final Set<Account> accountSet = new HashSet<>();

    @Override
    public Set<Account> findAll() {
        return accountSet;
    }

    @Override
    public void save(Account account) {
        accountSet.add(account);
    }
}
