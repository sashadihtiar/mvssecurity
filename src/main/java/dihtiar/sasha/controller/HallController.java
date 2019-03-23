package dihtiar.sasha.controller;

import dihtiar.sasha.model.HPlace;
import dihtiar.sasha.model.Hall;
import dihtiar.sasha.service.HPlaceService;
import dihtiar.sasha.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HallController {

    @Autowired
    HallService hallService;

    @Autowired
    HPlaceService hPlaceService;

    @GetMapping("/halls")
    public String getHall(Model model) {
        model.addAttribute("halls", hallService.getAll());
        return "hall";
    }

    @GetMapping(value = "/halls/new")
    public String addHall() {
        return "newhall";
    }

    @PostMapping(value = "/halls/new")
    public String addnewHall(@RequestParam("name") String name,
                             @RequestParam("rows") Long rows,
                             @RequestParam("places") Long places) {
        Hall hall = new Hall();
        hall.setName(name);
        hall.setPlaces(places);
        hall.setRows(rows);
        hallService.addnewHall(hall);
        return "redirect:/halls";
    }

    @GetMapping(value = "/halls/delete")
    public String delH() {
        return "halldelete";
    }

    @PostMapping(value = "/halls/delete")
    public String deleteHall(@RequestParam("name") String name) {
        hPlaceService.deleteByHallID(hallService.findHallByName(name).getId());
        hallService.deleteHall(name);
        return "redirect:/halls";
    }
}
