package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class ProfileWebController {

    @GetMapping("/detalhes")
    public String getProfileDetailPage() {
        return "profile/profile-detail-page";
    }

    @GetMapping("/alteracao")
    public String getEditProfilePage() {
        return "profile/profile-edit-page";
    }

}
