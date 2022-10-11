package br.dp.web.controller;

import br.dp.model.Animal;
import br.dp.web.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    @Autowired
    private AnimalService animalService;


    @GetMapping("/cadastrar-animal")
    public String getRegisterAnimalPage(final Animal animal) {

        return "adoption/register-animal-page";
    }

    @PostMapping("/create")
    public String create(final Animal animal) {

        final Long id = animalService.create(animal);

        if (id != -1) {
            return "redirect:/adocao/detalhes-animal/" + id;
        }

        return "redirect:/adocao/gerenciar-animais";
    }

    @GetMapping("/gerenciar-animais")
    public String getAdoptionPage(final Model model) {

        final List<Animal> animals = animalService.readAll();
        model.addAttribute("listaAnimal", animals);

        return "adoption/adoption-page";
    }

    @GetMapping("/detalhes-animal/{id}")
    public String getDetailPage(@PathVariable("id") final Long id, final Model model) {
        final Animal animalModel = animalService.readById(id);


        model.addAttribute("animal", animalModel);

        return "adoption/detail-animal-page";
    }

    @GetMapping("/editar-animal/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model) {

        model.addAttribute("animal", animalService.readById(id));

        return "adoption/edit-animal-page";
    }

    @PostMapping("/update")
    public String update(final Animal animal, final Model model) {

        animalService.update(animal);

        return getDetailPage(animal.getId(), model);

    }

    @PostMapping("/deletar")
    public String delete(@RequestParam(value = "password") final String senha, final Animal animal, final Model model) {

        if (senha.equals("123")) {
            System.out.println("opa");
            animalService.delete(animal.getId());

            return "redirect:/adocao/gerenciar-animais";

        } else {
            model.addAttribute("animal", animal);
            model.addAttribute("errorMessage", "A senha informada esta incorreta!");

            return "adoption/detail-animal-page";
        }

    }

    @GetMapping("/solicitacoes-adocao")
    public String getAdoptionRequestPage() {
        return "adoption/adoption-request-page";
    }

    @GetMapping("/detalhes-solicitacao-adocao")
    public String getDetailAdoptionRequestPage() {
        return "adoption/detail-adoption-request-page";
    }

}
