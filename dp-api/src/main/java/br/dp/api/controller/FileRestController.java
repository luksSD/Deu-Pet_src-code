package br.dp.api.controller;

import br.dp.api.service.AwsS3Service;
import br.dp.model.AnimalsArquives;
import br.dp.model.CampainsArquives;
import br.dp.model.UsersArquives;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Api(value = "File", tags = "Arquivos")
@CrossOrigin(origins = "*")
public class FileRestController {

    @Autowired
    AwsS3Service awss3Service;

    @GetMapping("/download-user-file/{id}")
    public ResponseEntity<String> downloadUserFile(@PathVariable("id") final long id){

        System.out.println("Chegou no FileRestController download user");
        return ResponseEntity.ok(awss3Service.downloadUserFile(id));
    }

    @GetMapping("/download-animal-files/{id}")
    public ResponseEntity<List<String>> downloadAnimalFiles(@PathVariable("id") final long id){

        System.out.println("Chegou no FileRestController");
        return ResponseEntity.ok(awss3Service.downloadAnimalFiles(id));
    }

    @GetMapping("/download-campain-file/{id}")
    public ResponseEntity<String> downloadCampainFile(@PathVariable("id") final long id){

        System.out.println("Chegou no FileRestController");
        return ResponseEntity.ok(awss3Service.downloadCampainFile(id));
    }

    @PostMapping("/upload-user-file")
    public ResponseEntity<Boolean> uploadUserFile(@RequestBody final UsersArquives userFile){

        System.out.println("Chegou no FileRestController upload user");
        return ResponseEntity.ok(awss3Service.uploadUserFile(userFile));
    }

    @PostMapping("/upload-campain-file")
    public ResponseEntity<Boolean> uploadCampainFile(@RequestBody final CampainsArquives campainFile){

        System.out.println("Chegou no FileRestController upload user");
        return ResponseEntity.ok(awss3Service.uploadCampainFile(campainFile));
    }

    @PostMapping("/upload-animal-files")
    public ResponseEntity<Boolean> uploadAnimalFiles(@RequestBody final List<AnimalsArquives> animalFiles){

        System.out.println("Chegou no FileRestController upload animal");
        return ResponseEntity.ok(awss3Service.uploadAnimalFiles(animalFiles));
    }

    @DeleteMapping("/delete-user-file/{id}")
    public ResponseEntity<Boolean> deleteUserFile(@PathVariable("id") final long id) {

        return ResponseEntity.ok(awss3Service.deleteUserFile(id));

    }

    @DeleteMapping("/delete-campaign-file/{id}")
    public ResponseEntity<Boolean> deleteCampaignFile(@PathVariable("id") final long id) {

        return ResponseEntity.ok(awss3Service.deleteCampaignFile(id));

    }

}
