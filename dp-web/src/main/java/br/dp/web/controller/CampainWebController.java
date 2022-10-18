package br.dp.web.controller;

import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import br.dp.web.service.CampainService;
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
    private String message = "";

    @Autowired
    private CampainService campainService;

    @GetMapping("/gerenciar-campanhas")
    public String getCampainsPage(final Model model) {

        final List<Campanha> campanhas = campainService.readAll();


        for (final Campanha campanha : campanhas) {
            CampainsArquives campainImg = new CampainsArquives();
            campainImg = campainService.loadCampainImg(campanha.getId());
            String pathName;

            if (campainImg.getPath() != null && !campainImg.getPath().isEmpty()) {
                pathName = "/images/campains/" + campainImg.getPath();
                if (!Files.exists(Path.of(System.getProperty("user.dir") + pathName))) {
                    pathName = CAMPAIN_DEFAULT_IMG;
                }
            } else {
                pathName = CAMPAIN_DEFAULT_IMG;
            }
            campanha.setCampainImg(pathName);
        }

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

        //Carrega imagens relacionas a a campanha
        final CampainsArquives campainImg = campainService.loadCampainImg(id);
        String pathName = "";

        if (campainImg.getPath() != null && !campainImg.getPath().isEmpty()) {
            if (!Files.exists(Path.of(System.getProperty("user.dir") + "/images/users/" + campainImg.getPath()))) {
                pathName = CAMPAIN_DEFAULT_IMG;
            } else {
                pathName = "/images/campains/" + campainImg.getPath();
            }
        } else {
            pathName = CAMPAIN_DEFAULT_IMG;
        }

        model.addAttribute("campanha", campanhaModel);
        model.addAttribute("img", pathName);

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
        final Long id = campainService.create(campanha);
        CampainsArquives campainImg = null;

        if (id != -1) {
            campainImg = new CampainsArquives();

            if (!file.isEmpty()) {
                final StringBuilder fileNames = new StringBuilder();

                final Path path = Paths.get(UPLOAD_DIRECTORY + id, file.getOriginalFilename());
                System.out.println(path.toAbsolutePath());

                if (!Files.exists(Path.of(UPLOAD_DIRECTORY + id))) {
                    try {
                        Files.createDirectories(Path.of(UPLOAD_DIRECTORY + id));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        //Limpa diretorio antes de adicionar imagens caso ja exista
                        Files.delete(Path.of(UPLOAD_DIRECTORY + id));
                        Files.createDirectories(Path.of(UPLOAD_DIRECTORY + id));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }

                }

                fileNames.append(file.getOriginalFilename() + " ");

                try {
                    Files.write(path, file.getBytes());

                    campainImg.setCampainId(id);
                    campainImg.setPath(id + "/" + file.getOriginalFilename());

                } catch (final IOException e) {
                    e.printStackTrace();
                }

            } else {
                campainImg.setCampainId(id);
                campainImg.setPath(CAMPAIN_DEFAULT_IMG);
            }

            if (campainImg != null) {
                final Long response = campainService.saveFileAttributes(campainImg);
            }

            message = "Campanha cadastrada com sucesso!";
            return "redirect:/campanhas/detalhes-campanha/" + id;
        } else {
            message = "Erro ao cadastrar campanha! Tente novamente.";
            return "redirect:/campanhas/cadastrar-campanha";
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {

        final CampainsArquives campainImg = campainService.loadCampainImg(id);
        final boolean response = campainService.deleteById(id);

        if (response) {
            //Exclui arquivos dentro do diretorio se existir
            final Path folderPath = Paths.get(System.getProperty("user.dir") + "/images/campains/" + id);

            if (Files.exists(folderPath)) {
                try {
                    final Path imgPath = Path.of(System.getProperty("user.dir") + "/images/campains/" + campainImg.getPath());
                    if (Files.exists(imgPath)) {
                        Files.delete(imgPath);
                    }
                } catch (final IOException e) {
                    e.printStackTrace();
                }

                //Exclui o diretorio
                try {
                    Files.delete(folderPath);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
            message = "Cadastro da instituição excluído com sucesso!";
            return "redirect:/campanhas/gerenciar-campanhas";
        } else {
            message = "Não foi possível excluir a instituição!";
            return "redirect:/instituicao/detalhes/" + id;
        }
    }
}







