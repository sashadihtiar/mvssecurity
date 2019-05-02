package dihtiar.sasha.service;

import dihtiar.sasha.model.Account;
import dihtiar.sasha.model.Users;

import java.util.List;

public interface AccountService {

    Account showYourAccount(Users user, String nameCurrency);

    List<Account> showYourAllAccount(Users users);

    void topUpAccount(Account account, Double amount);

    void minus(Account account, Double amount);

    void plus(Account account, Double amount);
}
