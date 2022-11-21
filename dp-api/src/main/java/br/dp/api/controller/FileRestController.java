package br.dp.api.controller;

import br.dp.api.service.AwsS3Service;
import br.dp.model.UsersArquives;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

}
