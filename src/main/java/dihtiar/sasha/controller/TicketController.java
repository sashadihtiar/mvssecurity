package dihtiar.sasha.controller;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Session;
import dihtiar.sasha.model.Ticket;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.HPlaceService;
import dihtiar.sasha.service.SessionService;
import dihtiar.sasha.service.TicketService;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;
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
                          @RequestParam("start_film") Time start_film,
                          @RequestParam("rows") Long rows,
                          @RequestParam("place") Long place) {
        Ticket ticket = new Ticket();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);
        ticket.setUsers(user);
        Session session = sessionService.findSessiontByFIlmNameAdnStart(film_name, start_film);
        if (session == null) {
            throw new UsernameNotFoundException("film not found");
        }
        ticket.setSession(session);
        HPlace hPlace = hPlaceService.findHPlaceForTicket(session.getHall().getName(), rows, place);
        ticket.sethPlace(hPlace);
        ticketService.addTicket(ticket);
        return "redirect:/ticket/your";
    }
}
