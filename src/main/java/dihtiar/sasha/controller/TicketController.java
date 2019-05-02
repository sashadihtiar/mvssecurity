package dihtiar.sasha.controller;

import dihtiar.sasha.exception.OutOfMoneyException;
import dihtiar.sasha.model.*;
import dihtiar.sasha.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;

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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);

        try {
            ticketService.buyTicket(film_name, start_film, rows, place, hall_name, user);
        } catch (OutOfMoneyException e) {
            return "redirect:/account/repacc";
        }
        return "redirect:/ticket/your";
    }
}
