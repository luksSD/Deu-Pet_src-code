package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    @GetMapping("/gerenciar")
    public String getAdoptionPage() {
        return "adoption/adoption-page";
    }

    @GetMapping("/cadastrar-animal")
    public String getRegisterAnimalPage() {
        return "adoption/register-animal-page";
    }

    @GetMapping("/solicitacoes-adocao")
    public String getAdoptionRequestPage() {
        return "adoption/adoption-request-page";
    }
}
