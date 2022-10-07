package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class ProfileWebController {


    @GetMapping("/detalhes")
    public String getProfileDetailPage() {
        return "profile/profile-detail-page";
    }

    @GetMapping("/alterar")
    public String getEditProfilePage() {
        return "profile/profile-edit-page";
    }

    @GetMapping("/alterar-senha/{senha}")
    public String getEditPasswordPage(@PathVariable("senha") final String senha) throws InterruptedException {
        System.out.println("ola a senha e: " + senha);
        return "profile/profile-detail-page";
    }

}
