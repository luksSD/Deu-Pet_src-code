package br.dp.web.controller;

import br.dp.model.UsersArquives;
import br.dp.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticateWebController {

    @Autowired
    UserService userService;

    @GetMapping("/acessar")
    public String getLoginPage() {
        return "users/login";
    }

    @GetMapping("/recuperar-senha")
    public String getRecoverPasswordPage() {
        return "users/recover-password";
    }

    @PostMapping("/validar-email")
    public String validateEmail(@RequestParam(value = "email") final String email, Model model) {

        boolean existEmail = userService.checkEmailExist(email);

        if(existEmail) {
            return "redirect:/email-validado";
        } else {
            model.addAttribute("errorMessage", true);
            return "users/recover-password";
        }
    }

    @GetMapping("/email-validado")
    public String validatedEmail(String email, Model model) {

        return "users/validated-email";

    }

}
