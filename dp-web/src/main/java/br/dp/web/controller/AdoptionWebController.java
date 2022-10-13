package br.dp.web.controller;

import br.dp.model.Animal;
import br.dp.model.ArquivoAnimal;
import br.dp.web.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    public static String uploadDirectory = System.getProperty("user.dir") + "/dp-web/src/main/resources/static/resources/images/animals/";
    private String message = "";
    private final Animal tempAnimal = null;

    @Autowired
    private AnimalService animalService;


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

        boolean firstFile = true;
        final List<ArquivoAnimal> fileAttributesList = new ArrayList<ArquivoAnimal>();

        //Verifica se o cadastro de animal foi registrado com sucesso
        if (id != -1) {

            if (files.size() > 0) {
                final StringBuilder fileNames = new StringBuilder();

                //Varre a lista de imagens enviadas para cadastro
                for (final MultipartFile file : files) {

                    final Path path = Paths.get(uploadDirectory + id, file.getOriginalFilename());
                    System.out.println(path.toAbsolutePath());

                    //verifica se o caminho de destino existe  e cria se nao esxistir
                    if (!Files.exists(Path.of(uploadDirectory + id))) {
                        try {
                            Files.createDirectories(Path.of(uploadDirectory + id));
                        } catch (final IOException e) {
                            e.printStackTrace();
                        }
                    }

                    fileNames.append(file.getOriginalFilename() + " ");
                    final ArquivoAnimal fileAttributes = new ArquivoAnimal();

                    try {
                        Files.write(path, file.getBytes());

                        fileAttributes.setAnimalID(id);
                        fileAttributes.setPath("/resources/images/animals/" + id + "/" + file.getOriginalFilename());
                        fileAttributes.setPrimary(firstFile);
                        firstFile = false;

                        fileAttributesList.add(fileAttributes);

                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                final ArquivoAnimal fileAttributes = new ArquivoAnimal();
                fileAttributes.setAnimalID(id);
                fileAttributes.setPath("/resources/images/animals/animal-default.jpg");
                fileAttributes.setPrimary(true);

                fileAttributesList.add(fileAttributes);
            }

            if (!fileAttributesList.isEmpty()) {
                animalService.saveFileAttributes(fileAttributesList);
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
        final List<ArquivoAnimal> imgsAttrList = animalService.loadAnimalImgs(id);

        model.addAttribute("animal", animalModel);
        model.addAttribute("imgs", imgsAttrList);
        if (!message.equals("")) {
            if (message.equals("Animal cadastrado com sucesso!")) {
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

        if (!response) {
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
            System.out.println("opa");
            response = animalService.delete(animal.getId());

            if (response) {
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
