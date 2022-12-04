package br.dp.web.controller;

import br.dp.model.Animal;
import br.dp.model.PessoaInteressaAnimal;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.AnimalService;
import br.dp.web.service.FileService;
import br.dp.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adocao")
public class AdoptionWebController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/animals/";
    public static String message = "";

    @Autowired
    private AnimalService animalService;

    @Autowired
    private FileService fileService;

    @Autowired
    private DpAuthenticationProvider authProvider;


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

        animal.setIdInstituicao(authProvider.getAuthenticatedUser().getId());
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
                animal.setPrimaryImagePath(imgList.get(0));
            } else {
                animal.setPrimaryImagePath(Constants.ANIMAL_DEFAULT_IMG);
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
            imgsList.add(Constants.ANIMAL_DEFAULT_IMG);
            model.addAttribute("imgs", imgsList);

        }

        model.addAttribute("animal", animalModel);

        if (!message.equals("")) {
            if (message.contains("sucesso")) {
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

    @GetMapping("/solicitacoes-adocao/{id}")
    public String getAdoptionRequestPage(@PathVariable("id") final Long id, Model model) {

        List<PessoaInteressaAnimal> adoptionRequestsList = animalService.readAdoptionsRequests(id);
        boolean solicitado = false;
        boolean aprovado = false;
        boolean recusado = false;

        for(PessoaInteressaAnimal adoptionRequest : adoptionRequestsList){
            String animalImg = adoptionRequest.getAnimal().getPrimaryImagePath();
            String pessoaImg = adoptionRequest.getPessoa().getProfileImg();

            if(animalImg == null || animalImg.equals("")) {
                adoptionRequest.getAnimal().setPrimaryImagePath(Constants.ANIMAL_DEFAULT_IMG);
            }
            if(pessoaImg == null || pessoaImg.equals("")) {
                adoptionRequest.getPessoa().setProfileImg(Constants.USER_DEFAULT_IMG);
            }

            if(adoptionRequest.getStatus().equals("solicitado")){
                solicitado = true;
            } else if(adoptionRequest.getStatus().equals("aprovado")){
                aprovado = true;
            } else if(adoptionRequest.getStatus().equals("recusado")){
                recusado = true;
            }

        }

        model.addAttribute("requests", adoptionRequestsList);
        model.addAttribute("solicitado", solicitado);
        model.addAttribute("aprovado", aprovado);
        model.addAttribute("recusado", recusado);

        return "adoption/adoption-request-page";
    }

    @GetMapping("/detalhes-solicitacao/{id}")
    public String getDetailAdoptionRequestPage(@PathVariable("id") final Long id, Model model) {

        PessoaInteressaAnimal adoptionRequest = animalService.readRequestById(id);
        String animalImg = adoptionRequest.getAnimal().getPrimaryImagePath();
        String pessoaImg = adoptionRequest.getPessoa().getProfileImg();

        if(animalImg == null || animalImg.equals("")) {
            adoptionRequest.getAnimal().setPrimaryImagePath(Constants.ANIMAL_DEFAULT_IMG);
        }

        if(pessoaImg == null || pessoaImg.equals("")) {
            adoptionRequest.getPessoa().setProfileImg(Constants.USER_DEFAULT_IMG);
        }

        model.addAttribute("request", adoptionRequest);

        return "adoption/detail-adoption-request-page";
    }

    @GetMapping("/solicitacao-aprovada/{id}")
    public String requestApproved(@PathVariable("id") final Long id) {

        final boolean response = animalService.requestStatusApproved(id);

        if (response) {
            message = "Solicitação aprovada com sucesso!";
        } else {
            message = "Erro ao realizar operação. Tente novamente!";
        }
        return "redirect:/adocao/detalhes-solicitacao/" + id;

    }

    @GetMapping("/solicitacao-recusada/{id}")
    public String requestDenied(@PathVariable("id") final Long id) {

        final boolean response = animalService.requestStatusDenied(id);

        if (response) {
            message = "Solicitação recusada com sucesso!";
        } else {
            message = "Erro ao realizar operação. Tente novamente!";
        }
        return "redirect:/adocao/detalhes-solicitacao/" + id;

    }

}
