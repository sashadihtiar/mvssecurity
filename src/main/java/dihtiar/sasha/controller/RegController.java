package dihtiar.sasha.controller;


import dihtiar.sasha.model.Role;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegController {


    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public String home() {
        return "redirect:users";
    }


    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "reg";
    }

    @GetMapping("/user")
    public String regUser() {
        return "reges";
    }

    @PostMapping("/user")
    public String singUp(@RequestParam("login") String login,
                         @RequestParam("password") String password) {
        Users users = new Users();
        Role role = new Role();
        role.setId(2L);
        users.setRole(role);
        users.setLogin(login);
        users.setPassword(password);
        usersService.addUser(users);
        return "redirect:/login";
    }
}
