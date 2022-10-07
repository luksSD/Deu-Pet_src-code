package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.model.Municipio;
import br.dp.web.service.CityService;
import br.dp.web.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/instituicao")
public class InstitutionWebController {

    @Autowired
    private InstitutionService instituicaoService;

    @Autowired
    private CityService cityService;

    @GetMapping("/gerenciar-instituicoes")
    public String getInstitutionsPage(final Model model) {

        final List<Instituicao> institutions = instituicaoService.readAll();
        model.addAttribute("institutions", institutions);

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
            return "redirect:/instituicao/detalhes/" + id;
        }

        return "redirect:/";
    }

    @GetMapping("/detalhes/{id}")
    public String getInstitutionDetailPage(@PathVariable("id") final Long id, final Model model) {

        final Instituicao institution = instituicaoService.readById(id);
        final Municipio city = cityService.readById(institution.getMunicipioId());

        model.addAttribute("instituicao", institution);
        model.addAttribute("cidade", city);

        return "institutions/institution-detail-page";
    }

    @GetMapping("/editar-instituicao/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model) {
        final Instituicao instituicaoModel = instituicaoService.readById(id);
        final Municipio city = cityService.readById(instituicaoModel.getMunicipioId());

        instituicaoModel.setMuinicipioNome(city.getNome());
        instituicaoModel.setUf(city.getUf());

        model.addAttribute("instituicao", instituicaoModel);

        return "institutions/edit-institution-page";
    }

    @PostMapping("/update")
    public String update(final Instituicao instituicao, final Model model) {

        instituicaoService.update(instituicao);

        return getInstitutionDetailPage(instituicao.getId(), model);

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {
        instituicaoService.deleteById(id);

        return "redirect:/instituicao/gerenciar-instituicoes";
    }
}