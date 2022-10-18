package br.dp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/images")
public class ImageHandlerController {

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
