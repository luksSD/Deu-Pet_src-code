package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.model.Municipio;
import br.dp.model.UsersArquives;
import br.dp.web.service.CityService;
import br.dp.web.service.FileService;
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

    @Autowired
    private FileService fileService;

    @GetMapping("/gerenciar-instituicoes")
    public String getInstitutionsPage(final Model model) {

        final List<Instituicao> institutions = instituicaoService.readAll();

        for (final Instituicao institution : institutions) {
            String userImgs = fileService.downloadUserFile(institution.getId());
            String pathName;

            if (userImgs != null && !userImgs.isEmpty()) {
                pathName = userImgs;
            } else {
                pathName = INSTITUTION_DEFAULT_IMG;
            }
            institution.setProfileImg(pathName);
        }

        model.addAttribute("institutions", institutions);

        if (!message.equals("")) {
            model.addAttribute("succesMessage", message);
            message = "";
        }

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

        if (id != -1) {
            if (!file.isEmpty()) {
                fileService.uploadFile(file, id,"user");
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

        //Carrega imagens
        String imageFile =  fileService.downloadUserFile(institution.getId());

        model.addAttribute("instituicao", institution);
        model.addAttribute("cidade", city);

        if (!imageFile.isEmpty() && imageFile.length() > 0 && imageFile != "" && imageFile != null) {
            model.addAttribute("img", imageFile);
        } else {
            model.addAttribute("img", INSTITUTION_DEFAULT_IMG);
        }

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

        if (!message.equals("")) {
            model.addAttribute("errorMessage", message);
            message = "";
        }

        return "institutions/edit-institution-page";
    }

    @PostMapping("/update")
    public String update(final Instituicao instituicao, final Model model) {

        final boolean response = instituicaoService.update(instituicao);

        if (response) {
            message = "Cadastro da instituição atualizado com sucesso!";
            return "redirect:/instituicao/detalhes/" + instituicao.getId();
        } else {
            message = "Não foi possível atualizar o cadastro da instituição. Tente novamente!";
            return "redirect:/instituicao/editar-instituicao/" + instituicao.getId();
        }

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Long id, final Model model) {

//        final UsersArquives userImg = instituicaoService.loadInstitutionImg(id);
        final boolean response = instituicaoService.deleteById(id);

        if (response) {
//            //Exclui arquivos dentro do diretorio se existir
//            final Path folderPath = Paths.get(System.getProperty("user.dir") + "/images/users/" + id);
//
//            if (Files.exists(folderPath)) {
//                try {
//                    final Path imgPath = Path.of(System.getProperty("user.dir") + "/images/users/" + userImg.getPath());
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

            message = "Cadastro da instituição excluído com sucesso!";
            return "redirect:/instituicao/gerenciar-instituicoes";
        } else {
            message = "Não foi possível excluir a instituição!";
            return "redirect:/instituicao/detalhes/" + id;
        }
    }


}
