package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instituicao")
public class InstitutionWebController {

    @GetMapping("/gerenciar-instituicoes")
    public String getInstitutionsPage() {
        return "institutions/institutions-page";
    }

    @GetMapping("/cadastrar-instituicao")
    public String getRegisterInstitutionPage() {
        return "institutions/create-institution-page";
    }


}
