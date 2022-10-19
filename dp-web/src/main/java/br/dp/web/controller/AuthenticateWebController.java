package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticateWebController {

    @GetMapping("/acessar")
    public String getLoginPage() {
        return "users/login";
    }
}
