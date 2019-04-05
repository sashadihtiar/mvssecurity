package dihtiar.sasha.controller;


import dihtiar.sasha.model.Account;
import dihtiar.sasha.model.Money;
import dihtiar.sasha.model.Payment;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.AccountService;
import dihtiar.sasha.service.PaymentService;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    AccountService accountService;

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/account")
    @ResponseBody
    public List<Account> yourAccount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username;
        username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);
        return accountService.showYourAllAccount(user);
    }

    @GetMapping(value = "/account/repacc")
    public String repAccount() {
        return "repAcc";
    }

    @PostMapping(value = "/account/repacc")
    public String replenishmentAccount(@RequestParam("sum") Double sum,
                                       @RequestParam("name") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username;
        username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);
        Account account = accountService.showYourAccount(user, name);
        accountService.topUpAccount(account, sum);
        Payment payment = new Payment();
        payment.setAccount(account);
        payment.setMoney(new Money(sum, account.getAmount().getMy—urrency()));
        paymentService.addPayment(payment);
        return "redirect:/account";

    }
}
