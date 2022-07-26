package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campanhas")
public class CampainWebController {

    @GetMapping("/gerenciar")
    public String getCampainsPage() {
        return "campain/campain-page";
    }

    @GetMapping("/cadastrar")
    public String getRegisterCampainPage() {
        return "campain/register-campain-page";
    }

}
