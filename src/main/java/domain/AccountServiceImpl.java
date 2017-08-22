package domain;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public double withdrawAndSaveOperation(double amount, Date date, Account account) {
        //operation add via account
        account.withdraw(amount);
        return account.checkAccountBalance().amount;
    }

    @Override
    public double depositAndSaveOperation(double amount, Date date, Account account) {
        //operation add via account
        account.depose(amount);
        return account.checkAccountBalance().amount;
    }

    @Override
    public Balance checkAccountBalance(Account account) {
        return account.checkAccountBalance();
    }
}
