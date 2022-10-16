package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.model.Municipio;
import br.dp.model.UsersArquives;
import br.dp.web.service.CityService;
import br.dp.web.service.InstitutionService;
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
@RequestMapping("/instituicao")
public class InstitutionWebController {

    public static final String INSTITUTION_DEFAULT_IMG = "/resources/images/institutions/institution-default.png";
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/images/users/";
    private String message = "";

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
    public String getRegisterInstitutionPage(final Instituicao instituicao, final Model model) {

        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "institutions/create-institution-page";
    }

    @PostMapping("/create")
    public String create(@RequestParam("file") final MultipartFile file, final Instituicao instituicao) {

        final Long id = instituicaoService.create(instituicao);
        UsersArquives userImage = null;

        if (id != -1) {

            userImage = new UsersArquives();

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

                    userImage.setUserId(id);
                    userImage.setPath(id + "/" + file.getOriginalFilename());

                } catch (final IOException e) {
                    e.printStackTrace();
                }
            } else {
                userImage.setUserId(id);
                userImage.setPath(INSTITUTION_DEFAULT_IMG);
            }

            if (userImage != null) {
                final Long response = instituicaoService.saveFileAttributes(userImage);
            }

            message = "Instituição cadastrada com sucesso!";
            return "redirect:/instituicao/detalhes/" + id;

        } else {
            message = "Erro ao cadastrar instituição! Tente novamente.";
            return "redirect:/instituicao/cadastrar-instituicao";
        }

    }

    @GetMapping("/detalhes/{id}")
    public String getInstitutionDetailPage(@PathVariable("id") final Long id, final Model model) {

        final Instituicao institution = instituicaoService.readById(id);
        final Municipio city = cityService.readById(institution.getMunicipioId());

        //Carrega imagens relacionas ao animal
        final UsersArquives userImg = instituicaoService.loadInstitutionImg(id);
        String pathName = "";

        if (!userImg.getPath().isEmpty()) {
            if (!Files.exists(Path.of(System.getProperty("user.dir") + "/images/users/" + userImg.getPath()))) {
                pathName = INSTITUTION_DEFAULT_IMG;
            } else {
                pathName = "/images/users/" + userImg.getPath();
            }
        } else {
            pathName = INSTITUTION_DEFAULT_IMG;
        }


        model.addAttribute("instituicao", institution);
        model.addAttribute("cidade", city);
        model.addAttribute("img", pathName);

        if (!message.equals("")) {
            if (message.equals("Instituição cadastrada com sucesso!") || message.equals("Cadastro da instituição atualizado com sucesso!")) {
                model.addAttribute("succesMessage", message);
            } else {
                model.addAttribute("errorMessage", message);
            }
            message = "";
        }

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
