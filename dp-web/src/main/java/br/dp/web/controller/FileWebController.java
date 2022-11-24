package br.dp.web.controller;

import br.dp.model.Campanha;
import br.dp.model.Instituicao;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/update-campaign")
    public String updateCampaignFile(@RequestParam("file") final MultipartFile file, Campanha campanha){

        if (fileService.uploadFile(file, campanha.getId(), "campanha")) {
            CampainWebController.message = "Imagem alterada com sucesso!";
        } else {
            CampainWebController.message = "Erro ao alterar imagem. Tente novamente!";
        }
        return "redirect:/campanhas/detalhes-campanha/" + campanha.getId();
    }


    @DeleteMapping("/delete-institution-file/{id}")
    public String deleteInstitutionFile(@PathVariable("id") final Long id){

        if (fileService.deleteUserFile(id)){
            InstitutionWebController.message = "Imagem excluída com sucesso!";
        } else {
            InstitutionWebController.message = "Erro ao excluir a imagem. Tente novamente!";
        }
        return "redirect:/instituicao/detalhes/" + id;
    }

    @DeleteMapping("/delete-campaign-file/{id}")
    public String deleteCampaignFile(@PathVariable("id") final Long id){

        if (fileService.deleteCampaignFile(id)){
            CampainWebController.message = "Imagem excluída com sucesso!";
        } else {
            CampainWebController.message = "Erro ao excluir a imagem. Tente novamente!";
        }
        return "redirect:/campanhas/detalhes-campanha/" + id;
    }
}
