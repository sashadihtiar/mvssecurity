package dihtiar.sasha.controller;

import dihtiar.sasha.model.Role;
import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.RoleService;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegController {

    @Autowired
    UsersService usersService;
    @Autowired
    RoleService roleService;

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
                         @RequestParam("password") String password,
                         @RequestParam("name") String name,
                         @RequestParam("surname") String surname) {
        Users users = new Users();
        users.setLogin(login);
        users.setPassword(password);
        users.setName(name);
        users.setSurname(surname);
        users.setRole(roleService.findRoleById(2L));
        usersService.addUser(users);
        return "redirect:/login";
    }

    @GetMapping("/yourprof/config")
    public String uppdatProf() {
        return "uppDa";
    }

    @PostMapping("/yourprof/config")
    public String uppD(@RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name,
                       @RequestParam("surname") String surname) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username = "";
        username = ((Users) obj).getLogin();

        Users user = usersService.findUserByLogin(username);
        if (login.length() > 1) {
            user.setLogin(login);
        }
        if (password.length() > 1) {
            user.setPassword(password);
        }
        if (name.length() > 1) {
            user.setName(name);
        }
        if (surname.length() > 1) {
            user.setSurname(surname);
        }
        usersService.addUser(user);
        return "redirect:/users";
    }
}
