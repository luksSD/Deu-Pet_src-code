package br.dp.web.controller;

import br.dp.model.Animal;
import br.dp.model.AnimalsArquives;
import br.dp.web.service.AnimalService;
import br.dp.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    public static final String ANIMAL_DEFAULT_IMG = "/resources/images/animals/animal-default.jpg";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/animals/";
    private String message = "";

    @Autowired
    private AnimalService animalService;

    @Autowired
    private FileService fileService;


    @GetMapping("/cadastrar-animal")
    public String getRegisterAnimalPage(final Animal animal, final Model model) {

        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "adoption/register-animal-page";
    }

    @PostMapping("/create")
    public String create(@RequestParam("file") final List<MultipartFile> files, final Animal animal, final Model model) {

        //Cadastra animal
        final Long id = animalService.create(animal);

        //Verifica se o cadastro de animal foi registrado com sucesso
        if (id != -1) {
            if (files.size() > 0) {
                fileService.uploadFiles(files, id);
            }

            message = "Animal cadastrado com sucesso!";
            return "redirect:/adocao/detalhes-animal/" + id;
        } else {
            message = "Erro ao cadastrar anima! Tente novamente.";
            return "redirect:/adocao/cadastrar-animal";
        }

    }

    @GetMapping("/gerenciar-animais")
    public String getAdoptionPage(final Model model) {

        final List<Animal> animals = animalService.readAll();

        for (final Animal animal : animals) {
            List<String> imgList = fileService.downloadAnimalFiles(animal.getId());

            if (imgList.size() > 0) {
                model.addAttribute("imgs", imgList);
            } else {
                imgList = new ArrayList<String>();
                imgList.add(ANIMAL_DEFAULT_IMG);
                model.addAttribute("imgs", imgList);
            }
        }

        model.addAttribute("listaAnimal", animals);
        if (!message.equals("")) {
            model.addAttribute("succesMessage", message);
            message = "";
        }

        return "adoption/adoption-page";
    }

    @GetMapping("/detalhes-animal/{id}")
    public String getDetailPage(@PathVariable("id") final Long id, final Model model) {

        final Animal animalModel = animalService.readById(id);

        //Carrega imagens relacionas ao animal
        List<String> imgsList = fileService.downloadAnimalFiles(id);

        if (imgsList.size() != 0) {
            model.addAttribute("imgs", imgsList);
        } else {
            imgsList = new ArrayList<String>();
            imgsList.add(ANIMAL_DEFAULT_IMG);
            model.addAttribute("imgs", imgsList);

        }

        model.addAttribute("animal", animalModel);

        if (!message.equals("")) {
            if (message.equals("Animal cadastrado com sucesso!") || message.equals("Cadastro do animal atualizado com sucesso!")) {
                model.addAttribute("succesMessage", message);
            } else {
                model.addAttribute("errorMessage", message);
            }
            message = "";
        }

        return "adoption/detail-animal-page";
    }

    @GetMapping("/editar-animal/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model) {

        model.addAttribute("animal", animalService.readById(id));
        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "adoption/edit-animal-page";
    }

    @PostMapping("/update")
    public String update(final Animal animal, final Model model) {

        final boolean response = animalService.update(animal);

        if (response) {
            message = "Cadastro do animal atualizado com sucesso!";
            return "redirect:/adocao/detalhes-animal/" + animal.getId();
        } else {
            message = "Não foi possível atualizar o cadastro do animal. Tente novamente!";
            return "redirect:/adocao/editar-animal/" + animal.getId();
        }

    }

    @PostMapping("/deletar")
    public String delete(@RequestParam(value = "password") final String senha, final Animal animal, final Model model) {

        boolean response = false;

        if (senha.equals("123")) {

//            final List<AnimalsArquives> imgsList = animalService.loadAnimalImgs(animal.getId());
            response = animalService.delete(animal.getId());

            if (response) {
                //Exclui arquivos dentro do diretorio se existir
//                final String pathName = System.getProperty("user.dir") + "/images/animals/" + animal.getId();
//                if (Files.exists(Path.of(pathName))) {
//                    for (final AnimalsArquives animalImg : imgsList) {
//                        try {
//                            Files.delete(Path.of(System.getProperty("user.dir") + "/images/animals/" + animalImg.getPath()));
//                        } catch (final IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    //Exclui o diretorio
//                    try {
//                        Files.delete(Path.of(pathName));
//                    } catch (final IOException e) {
//                        e.printStackTrace();
//                    }
//                }

                message = "Cadastro do animal excluído com sucesso!";
                return "redirect:/adocao/gerenciar-animais";
            } else {
                message = "Não foi possível excluir o animal!";
                return "redirect:/adocao/detalhes-animal/" + animal.getId();
            }
        } else {
            message = "A senha informada esta incorreta!";
            return "redirect:/adocao/detalhes-animal/" + animal.getId();
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
