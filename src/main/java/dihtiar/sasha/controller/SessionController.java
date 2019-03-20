package dihtiar.sasha.controller;

import dihtiar.sasha.model.Film;
import dihtiar.sasha.model.Hall;
import dihtiar.sasha.model.Session;
import dihtiar.sasha.service.FilmService;
import dihtiar.sasha.service.HallService;
import dihtiar.sasha.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Time;

@Controller
public class SessionController {
    @Autowired
    SessionService sessionService;

    @Autowired
    FilmService filmService;

    @Autowired
    HallService hallService;

    @GetMapping(value = "/session")
    public String getSession(Model model) {
        model.addAttribute("sessions", sessionService.getAll());
        return "session";
    }

    @GetMapping(value = "/session/new")
    public String addSes() {
        return "newsession";
    }

    @PostMapping(value = "/session/new")
    public String addnewSession(@RequestParam("film") String name_film,
                                @RequestParam("hall") String name_hall,
                                @RequestParam("start") Time start) {
        Film film = filmService.findFilmByName(name_film);
        Hall hall = hallService.findHallByName(name_hall);
        Session session = new Session();
        session.setFilm(film);
        session.setHall(hall);
        session.setStart(start);
        sessionService.addSession(session);
        return "redirect:/session";

    }

    @GetMapping(value = "/places")
    public String getPlaces() {
        return "places";
    }

    @PostMapping(value = "/places")
    public String getFreePl(Model model,@RequestParam("film_name") String film_name,
                                  @RequestParam("film_start") Time film_start) {
        Session session = sessionService.findSessiontByFIlmNameAdnStart(film_name,film_start);
        model.addAttribute("qwe",sessionService.freePlace(session));
        return "ah";
    }
}
