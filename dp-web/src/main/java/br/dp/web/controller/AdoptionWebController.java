package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    @GetMapping("/gerenciar-animais")
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

    @GetMapping("/detalhes-solicitacao-adocao")
    public String getDetailAdoptionRequestPage() {
        return "adoption/detail-adoption-request-page";
    }

    @GetMapping("/detalhes-animal")
    public String getDetailPage() {
        return "adoption/detail-animal-page";
    }

    @GetMapping("/editar-animal")
    public String getEditPage() {
        return "adoption/edit-animal-page";
    }
}
