package br.dp.web.controller;

import br.dp.model.Instituicao;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/files")
public class FileWebController {

    @Autowired
    FileService fileService;

    @Autowired
    private DpAuthenticationProvider authProvider;

    @PostMapping("/update-instituicao")
    public String updateFile(@RequestParam("file") final MultipartFile file, Instituicao instituicao){

        if (fileService.uploadFile(file, instituicao.getId(), instituicao.getTipo())) {
            InstitutionWebController.message = "Imagem alterada com sucesso!";
        } else {
            InstitutionWebController.message = "Erro ao alterar imagem! Tente novamente.";
        }
        return "redirect:/instituicao/detalhes/" + instituicao.getId();
    }

    private final String uploadDirectory = System.getProperty("user.dir");

    @GetMapping("/animals/{id}/{img}")
    @ResponseBody
    public byte[] requestAnimalImage(@PathVariable("id") final Long id, @PathVariable("img") final String imgName) {
        final File imagemArquivo = new File(uploadDirectory + "/images/animals/" + id + "/" + imgName);
        try {
            return Files.readAllBytes(imagemArquivo.toPath());
        } catch (final IOException e) {
            return null;
        }
    }

    @GetMapping("/users/{id}/{img}")
    @ResponseBody
    public byte[] requestUserImage(@PathVariable("id") final Long id, @PathVariable("img") final String imgName) {
        final File imagemArquivo = new File(uploadDirectory + "/images/users/" + id + "/" + imgName);
        try {
            return Files.readAllBytes(imagemArquivo.toPath());
        } catch (final IOException e) {
            return null;
        }
    }

    @GetMapping("/campains/{id}/{img}")
    @ResponseBody
    public byte[] requestCampainImage(@PathVariable("id") final Long id, @PathVariable("img") final String imgName) {
        final File imagemArquivo = new File(uploadDirectory + "/images/campains/" + id + "/" + imgName);
        try {
            return Files.readAllBytes(imagemArquivo.toPath());
        } catch (final IOException e) {
            return null;
        }
    }
}
