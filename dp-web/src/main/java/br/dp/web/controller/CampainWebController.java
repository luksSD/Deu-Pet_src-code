package br.dp.web.controller;

import br.dp.model.Campanha;
import br.dp.web.service.CampainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/campanhas")
public class CampainWebController {

    @Autowired
    private CampainService campainService;

    @GetMapping("/gerenciar-campanhas")
    public String getCampainsPage() {
        System.out.println("cai aqui");
        final List<Campanha> campanha = campainService.readAll();

        return "campains/campains-page";
    }

    @GetMapping("/detalhes-campanha/{id}")
    public String getDetailPage(@PathVariable("id") final Long id, final Model model) {
        final Campanha campanha = campainService.readById(id);
        model.addAttribute("campanha", campanha);
        //aqui estou retornando null porque ainda não tem a página
        return null;
    }

    @GetMapping("/editar-campanha/{id}")
    public String getEditPage(@PathVariable("id") final long id, final Model model) {
        final Campanha campanha = campainService.readById(id);
        model.addAttribute("campanha", campanha);
        //aqui estou retornando null porque ainda não tem a página
        return null;
    }

    @PostMapping("/update")
    public String update(final Campanha campanha, final Model model) {
        campainService.update(campanha);

        //aqui vai retornar a tela de detalhes
        return null;
    }

    @GetMapping("/cadastrar-campanhas")
    public String getRegisterCampainPage() {
        return "campains/create-campain-page";
    }

    @PostMapping("/create")
    public String create(final Campanha campanha) {
        final Long id = campainService.create(campanha);

        if (id != -1) {
            return "redirect:/";
        }
        return "redirect:/campanha/gerenciar-campanha";
    }

    @GetMapping("/deletar/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {
        campainService.deleteById(id);
        return "redirect:/";
    }

}
