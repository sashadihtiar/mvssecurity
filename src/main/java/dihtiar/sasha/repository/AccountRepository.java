package dihtiar.sasha.repository;


import dihtiar.sasha.model.Account;
import dihtiar.sasha.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAmount_My—urrency_—urrencyNameAndUsers(String nameCurrency, Users user);

    List<Account> findByUsers(Users users);

}
