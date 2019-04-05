package dihtiar.sasha.service;

import dihtiar.sasha.model.Account;
import dihtiar.sasha.model.Money;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account showYourAccount(Users user, String nameCurrency) {
        return accountRepository.findByAmount_My—urrency_—urrencyNameAndUsers(nameCurrency, user);
    }

    @Override
    public List<Account> showYourAllAccount(Users users) {
        return accountRepository.findByUsers(users);
    }

    @Transactional
    @Override
    public void topUpAccount(Account account, Double amount) {
        account.setAmount(new Money(account.getAmount().getAmountMoney() + amount, account.getAmount().getMy—urrency()));
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void minus(Account account, Double amount) {
        account.setAmount(new Money(account.getAmount().getAmountMoney() - amount, account.getAmount().getMy—urrency()));
        accountRepository.save(account);
    }
}
