package br.dp.api.controller;

import br.dp.api.service.AwsS3Service;
import br.dp.api.service.CampanhaService;
import br.dp.model.CampainsArquives;
import br.dp.model.Campanha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campanha")
@Api(value = "Campanha", tags = "Campanha")
@CrossOrigin(origins = "*")
public class CampanhaRestController {

    @Autowired
    private CampanhaService service;

    @Autowired
    private AwsS3Service fileService;

    @ApiOperation(value = "Retorna uma lista de Campanhas")
    @GetMapping("/read-all")
    public ResponseEntity<List<Campanha>> readAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @ApiOperation(value = "Retorna uma campanha baseado no ID")
    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Campanha> readById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.readById(id));
    }

    @ApiOperation(value = "Atualiza o cadastro de uma campanha")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody final Campanha campanha) {
        return ResponseEntity.ok(service.update(campanha));
    }

    @ApiOperation(value = "Cria uma nova campanha", consumes = "application/json")
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody final Campanha campanha) {
        return ResponseEntity.ok(service.create(campanha));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

        if(service.delete(id)){
            return ResponseEntity.ok(fileService.deleteCampaignFile(id));
        }

        return ResponseEntity.ok(false);
    }

}
