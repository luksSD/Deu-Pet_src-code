package br.dp.web.controller;

import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.CampainService;
import br.dp.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/campanhas")
public class CampainWebController {

    public static final String CAMPAIN_DEFAULT_IMG = "/resources/images/campains/campain-default.png";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/campains/";
    public static String message = "";

    @Autowired
    private CampainService campainService;

    @Autowired
    private FileService fileService;

    @Autowired
    private DpAuthenticationProvider authProvider;

    @GetMapping("/gerenciar-campanhas")
    public String getCampainsPage(final Model model) {

        final List<Campanha> campanhas = campainService.readAll();

        model.addAttribute("listaCampanhas", campanhas);

        if (!message.equals("")) {
            model.addAttribute("succesMessage", message);
            message = "";
        }

        return "campains/campains-page";
    }

    @GetMapping("/detalhes-campanha/{id}")
    public String getDetailPage(@PathVariable("id") final Long id, final Model model) {
        final Campanha campanhaModel = campainService.readById(id);

        //Carrega imagens relacionas a campanha
        final String imageFile = fileService.downloadCampainFile(id);

        if (imageFile != null && imageFile != "") {
            model.addAttribute("img", imageFile);
        } else {
            model.addAttribute("img", CAMPAIN_DEFAULT_IMG);
        }
        model.addAttribute("campanha", campanhaModel);

        if (!message.equals("")) {
            if (message.equals("Campanha cadastrada com sucesso!") || message.equals("Cadastro de campanha atualizado com sucesso!")) {
                model.addAttribute("succesMessage", message);
            } else {
                model.addAttribute("errorMessage", message);
            }
            message = "";
        }

        return "campains/detail-campains-page";
    }

    @GetMapping("/editar-campanha/{id}")
    public String getEditPage(@PathVariable("id") final Long id, final Model model) {
        final Campanha campanhaModel = campainService.readById(id);
        model.addAttribute("campanha", campanhaModel);

        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "campains/edit-campains-page";
    }

    @PostMapping("/update")
    public String update(final Campanha campanha, final Model model) {
        final boolean response = campainService.update(campanha);

        if (response) {
            message = "Cadastro da campanha atualizado com sucesso!";
            return "redirect:/campanhas/detalhes-campanha/" + campanha.getId();
        } else {
            message = "Não foi possível atualizar o cadastro da campanha. Tente novamente!";
            return "redirect:/campanhas/editar-campanha/" + campanha.getId();
        }

    }

    @GetMapping("/cadastrar-campanhas")
    public String getRegisterCampainPage(final Campanha campanha, final Model model) {

        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "campains/create-campain-page";
    }

    @PostMapping("/create")
    public String create(@RequestParam("file") final MultipartFile file, final Campanha campanha) {

        campanha.setInstituicaoId(authProvider.getAuthenticatedUser().getId());
        final Long id = campainService.create(campanha);

        if (id != -1) {
            if (!file.isEmpty()) {
                fileService.uploadFile(file, id,"campain");
            }

            message = "Campanha cadastrada com sucesso!";
            return "redirect:/campanhas/detalhes-campanha/" + id;
        } else {
            message = "Erro ao cadastrar campanha! Tente novamente.";
            return "redirect:/campanhas/cadastrar-campanhas";
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {

//        final CampainsArquives campainImg = campainService.loadCampainImg(id);
        final boolean response = campainService.deleteById(id);

        if (response) {
//            //Exclui arquivos dentro do diretorio se existir
//            final Path folderPath = Paths.get(System.getProperty("user.dir") + "/images/campains/" + id);
//
//            if (Files.exists(folderPath)) {
//                try {
//                    final Path imgPath = Path.of(System.getProperty("user.dir") + "/images/campains/" + campainImg.getPath());
//                    if (Files.exists(imgPath)) {
//                        Files.delete(imgPath);
//                    }
//                } catch (final IOException e) {
//                    e.printStackTrace();
//                }
//
//                //Exclui o diretorio
//                try {
//                    Files.delete(folderPath);
//                } catch (final IOException e) {
//                    e.printStackTrace();
//                }
//            }
            message = "Cadastro da campanha excluído com sucesso!";
            return "redirect:/campanhas/gerenciar-campanhas";
        } else {
            message = "Não foi possível excluir a campanha!";
            return "redirect:/campanhas/detalhes-campanha/" + id;
        }
    }
}







