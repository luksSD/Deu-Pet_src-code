package br.dp.api.controller;

import br.dp.api.service.AnimalService;
import br.dp.model.Animal;
import br.dp.model.PessoaInteressaAnimal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal")
@Api(value = "Animal", tags = "Animal")
@CrossOrigin(origins = "*")
public class AnimalRestController {

    @Autowired
    private AnimalService animalService;

    @ApiOperation(value = "Retorna uma lista de Animais")
    @GetMapping("/read-all")
    public ResponseEntity<List<Animal>> readAll() {

        return ResponseEntity.ok(animalService.readAll());

    }

    @ApiOperation(value = "Retorna um animal baseado no ID")
    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Animal> readById(@PathVariable("id") final long id) {

        return ResponseEntity.ok(animalService.readById(id));

    }

    @ApiOperation(value = "Cria um novo animal", consumes = "application/json")
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody final Animal animal) {

        return ResponseEntity.ok(animalService.create(animal));

    }

    @ApiOperation(value = "Atualiza o cadastro de um animal")
    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody final Animal animal) {

        return ResponseEntity.ok(animalService.update(animal));

    }

    @ApiOperation(value = "Deleta o cadastro de um animal")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

        return ResponseEntity.ok(animalService.delete(id));
    }

    @ApiOperation(value = "Registra solicitação de adoção")
    @PostMapping("/adoption-request")
    public ResponseEntity<Long> adoptionRequest(@RequestBody final PessoaInteressaAnimal adoptionRequest) {

        return ResponseEntity.ok(animalService.adoptionRequest(adoptionRequest));
    }

    @ApiOperation(value = "Retorna lista contendo as solicitações de adoção referentes a instituição")
    @GetMapping("/read-all-adoptions-requests/{id}")
    public ResponseEntity<List<PessoaInteressaAnimal>> readAdoptionsRequests(@PathVariable("id") long id) {

        return ResponseEntity.ok(animalService.readAdoptionsRequests(id));
    }

    @ApiOperation(value = "Retorna detalhes da solicitação de adoção")
    @GetMapping("/read-request-by-id/{id}")
    public ResponseEntity<PessoaInteressaAnimal> readRequestById(@PathVariable("id") long id) {

        return ResponseEntity.ok(animalService.readRequestById(id));
    }

    @ApiOperation(value = "Atualiza o status da solicitação de adoção para aprovado")
    @PutMapping("/request-status-approved/{id}")
    public ResponseEntity<Boolean> requestStatusApproved(@PathVariable("id") long id) {

        boolean result;
        PessoaInteressaAnimal request = animalService.readRequestById(id);
        request.setStatus("aprovado");
        request.getAnimal().setSituacao("adotado");

        result = animalService.updateRequestStatus(request) && animalService.updateAnimalStatus(request.getAnimal());

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Atualiza o status da solicitação de adoção para recusado")
    @PutMapping("/request-status-denied/{id}")
    public ResponseEntity<Boolean> requestStatusDenied(@PathVariable("id") long id) {

        boolean result;
        PessoaInteressaAnimal request = animalService.readRequestById(id);
        request.setStatus("recusado");

        result = animalService.updateRequestStatus(request);

        return ResponseEntity.ok(result);
    }
}
