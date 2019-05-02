package dihtiar.sasha.controller;

import dihtiar.sasha.model.Users;
import dihtiar.sasha.service.PaymentService;
import dihtiar.sasha.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    UsersService usersService;

    @GetMapping("/payments")
    public String showPayment(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object obj = auth.getPrincipal();
        String username;
        username = ((Users) obj).getLogin();
        Users user = usersService.findUserByLogin(username);
        model.addAttribute("pay", paymentService.showYourPayment(user));
        return "payments";
    }
}
