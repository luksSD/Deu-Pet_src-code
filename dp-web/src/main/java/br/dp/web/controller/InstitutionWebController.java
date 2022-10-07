package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.web.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instituicao")
public class InstitutionWebController {

    @Autowired
    private InstitutionService instituicaoService;

    @GetMapping("/gerenciar-instituicoes")
    public String getInstitutionsPage() {
        return "institutions/institutions-page";
    }

    @GetMapping("/cadastrar-instituicao")
    public String getRegisterInstitutionPage(final Instituicao instituicao) {
        return "institutions/create-institution-page";
    }

    @PostMapping("/create")
    public String create(final Instituicao instituicao) {

        final Long id = instituicaoService.create(instituicao);

        if (id != -1) {
            return "redirect:/instituicao/detalhes";
        }

        return "redirect:/";
    }


}
