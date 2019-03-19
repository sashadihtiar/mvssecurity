package dihtiar.sasha.controller;

import dihtiar.sasha.model.Film;
import dihtiar.sasha.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping(name = "/films")
    public String getFilm(Model model) {
        model.addAttribute("films", filmService.getAll());
        return "films";
    }

    @GetMapping(value = "/films/delete")
    public String del() {
        return "delete";
    }

    @PostMapping(value = "/films/delete")
    public String delete(@RequestParam("name") String name) {
        filmService.deleteFilmByName(name);
        return "redirect:/films";
    }

    @GetMapping(value = "/films/new")
    public String addFilm() {
        return "filmnew";
    }

    @PostMapping(value = "/films/new")
    public String addnewFilm(@RequestParam("name") String name,
                             @RequestParam("duration") Long duration) {
        Film film = new Film();
        film.setName(name);
        film.setDuration(duration);
        filmService.addnewFilm(film);
        return "redirect:/films";
    }
}
