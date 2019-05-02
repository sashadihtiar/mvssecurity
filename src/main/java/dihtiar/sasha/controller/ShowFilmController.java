package dihtiar.sasha.controller;


import dihtiar.sasha.exception.OutOfMoneyException;
import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Session;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.SessionService;
import dihtiar.sasha.service.TicketService;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShowFilmController {
    @Autowired
    UsersService usersService;

    @Autowired
    SessionService sessionService;

    @Autowired
    TicketService ticketService;

    @GetMapping(value = "/gogo")
    public String toFilm() {
        return "gogogo";
    }

    @PostMapping(value = "/gogo")
    public String toF(@RequestParam("film_name") String filmName) {
        int i = 0;
        List<Users> listUsers = usersService.getAll();
        List<Session> sessionList = sessionService.findSessionByFilmName(filmName);
        for (Session session : sessionList) {
            for (Users us : listUsers) {
                List<HPlace> hPlaceList = sessionService.freePlace(session);
                for (HPlace hp : hPlaceList) {
                    if (i > 10) {
                        break;
                    }
                    try {
                        ticketService.buyTicket(session.getFilm().getName(),
                                session.getStart(), hp.getR(), hp.getP(), session.getHall().getName(), us);
                        i++;
                    } catch (OutOfMoneyException ex) {
                        return "redirect:/account/repacc";
                    }
                }
                i = 0;
            }
        }
        return "redirect:/users";
    }
}
