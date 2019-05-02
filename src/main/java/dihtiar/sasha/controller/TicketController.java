package dihtiar.sasha.controller;

import dihtiar.sasha.model.*;
import dihtiar.sasha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    SessionService sessionService;

    @Autowired
    HPlaceService hPlaceService;

    @Autowired
    UsersService usersService;

    @Autowired
    AccountService accountService;

    @Autowired
    PaymentService paymentService;

    @GetMapping(value = "/ticket/your")
    public String yorUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = "";
        username = ((Users) obj).getLogin();
        model.addAttribute("yourticket", ticketService.getyourTicket(username));
        return "ticketyou";
    }

    @GetMapping(value = "ticket/buy")
    public String buyTicket() {
        return "buyticket";
    }

    @PostMapping(value = "ticket/buy")
    public String buyTick(@RequestParam("film_name") String film_name,
                          @RequestParam("start_film") Timestamp start_film,
                          @RequestParam("rows") Long rows,
                          @RequestParam("place") Long place,
                          @RequestParam("name_hall") String hall_name) {
        Ticket ticket = new Ticket();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);
        ticket.setUsers(user);
        Session session = sessionService.findSessiontByFIlmNameAndStartAndHall_name(film_name, start_film, hall_name);
        if (session == null) {
            throw new UsernameNotFoundException("film not found");
        }
        ticket.setSession(session);
        List<HPlace> list = sessionService.freePlace(session);
        HPlace hPlace = null;
        for (HPlace hp : list) {
            if (hp.getR() == rows && hp.getP() == place) {
                hPlace = hp;
            }
        }
        Account account = accountService.showYourAccount(user, hPlace.getCost().getMyCurrency().getCurrencyName());
        if (account.getAmount().getAmountMoney() < hPlace.getCost().getAmountMoney()) {
            return "redirect:/account/repacc";
        } else {
            ticket.sethPlace(hPlace);
            ticketService.addTicket(ticket);
            accountService.minus(account, hPlace.getCost().getAmountMoney());
            Payment payment = new Payment();
            payment.setAccount(account);
            payment.setMoney(new Money(-hPlace.getCost().getAmountMoney(), account.getAmount().getMyCurrency()));
            paymentService.addPayment(payment);
            Users corp = usersService.findUserById(1l);
            Account corpAcc = accountService.showYourAccount(corp, hPlace.getCost().getMyCurrency().getCurrencyName());
            accountService.plus(corpAcc, hPlace.getCost().getAmountMoney());
            Payment payment1 = new Payment();
            payment1.setAccount(corpAcc);
            payment1.setMoney(new Money(hPlace.getCost().getAmountMoney(), corpAcc.getAmount().getMyCurrency()));
            paymentService.addPayment(payment1);
        }
        return "redirect:/ticket/your";
    }
}
