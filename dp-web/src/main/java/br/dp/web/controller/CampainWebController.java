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
    public String getCampainsPage(final Model model) {

        final List<Campanha> campanhas = campainService.readAll();
        model.addAttribute("listaCampanhas", campanhas);

        return "campains/campains-page";
    }

    @GetMapping("/detalhes-campanha/{id}")
    public String getDetailPage(@PathVariable("id") final Long id, final Model model) {
        final Campanha campanhaModel = campainService.readById(id);
        model.addAttribute("campanha", campanhaModel);

        return "campains/detail-campains-page";
    }

    @GetMapping("/editar-campanha/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model) {
        final Campanha campanhaModel = campainService.readById(id);
        model.addAttribute("campanha", campanhaModel);

        return "campains/edit-campains-page";
    }

    @PostMapping("/update")
    public String update(final Campanha campanha, final Model model) {
        campainService.update(campanha);

        return "redirect:/";
        //Depois que o detail esiver pronto deixa o de baixo
        //return getDetailPage(campanha.getId(), model);
    }

    @GetMapping("/cadastrar-campanhas")
    public String getRegisterCampainPage(final Campanha campanha) {

        return "campains/create-campain-page";
    }

    @PostMapping("/create")
    public String create(final Campanha campanha) {
        final Long id = campainService.create(campanha);

        if (id != -1) {
            return "redirect:/";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {
        campainService.deleteById(id);
        return "redirect:/campanhas/gerenciar-campanhas";
    }

}
