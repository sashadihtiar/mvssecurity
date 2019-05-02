package dihtiar.sasha.service;

import dihtiar.sasha.exception.OutOfMoneyException;
import dihtiar.sasha.model.*;
import dihtiar.sasha.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    SessionService sessionService;

    @Autowired
    AccountService accountService;

    @Autowired
    PaymentService paymentService;

    @Override

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getyourTicket(String login) {
        return ticketRepository.findTicketByUsers_Login(login);
    }

    @Transactional
    @Override
    public void addTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Transactional
    @Override
    public void buyTicket(String filmName, Timestamp startFilm, Long rows, Long place, String hallName, Users user) throws OutOfMoneyException {
        Ticket ticket = new Ticket();
        ticket.setUsers(user);
        Session session = sessionService.findSessiontByFIlmNameAndStartAndHall_name(filmName, startFilm, hallName);
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
            throw new OutOfMoneyException("you don't have money!!!");
        } else {
            ticket.sethPlace(hPlace);
            accountService.minus(account, hPlace.getCost().getAmountMoney());
            Payment payment = new Payment();
            payment.setAccount(account);
            payment.setMoney(new Money(-hPlace.getCost().getAmountMoney(), account.getAmount().getMyCurrency()));
            paymentService.addPayment(payment);
            ticket.setPayment(payment);
            addTicket(ticket);
            Users corp = usersService.findUserById(1l);
            Account corpAcc = accountService.showYourAccount(corp, hPlace.getCost().getMyCurrency().getCurrencyName());
            accountService.plus(corpAcc, hPlace.getCost().getAmountMoney());
            Payment payment1 = new Payment();
            payment1.setAccount(corpAcc);
            payment1.setMoney(new Money(hPlace.getCost().getAmountMoney(), corpAcc.getAmount().getMyCurrency()));
            paymentService.addPayment(payment1);
        }
    }
}
