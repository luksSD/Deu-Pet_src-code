package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campanhas")
public class CampainWebController {

    @GetMapping("/gerenciar")
    public String getCampainsPage() {
        return "campains/campains-page";
    }

    @GetMapping("/cadastrar")
    public String getRegisterCampainPage() {
        return "campains/create-campain-page";
    }

}
