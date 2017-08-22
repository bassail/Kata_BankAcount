package domain;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void add(Account account) {
        accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        List<Account> candidates = new ArrayList<>();
        candidates.addAll(accountRepository.findAll());
        return candidates;
    }
}
