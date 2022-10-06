package br.dp.web.controller;

import br.dp.model.Instituicao;
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
    public String getRegisterInstitutionPage(final Instituicao instituicao) {
        return "institutions/create-institution-page";
    }


}
