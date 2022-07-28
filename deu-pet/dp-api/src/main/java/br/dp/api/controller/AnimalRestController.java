package br.dp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dp.api.service.AnimalService;
import br.dp.model.Animal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/animal")
@Api(value = "Animal", tags = "Animal")
@CrossOrigin(origins = "*")
public class AnimalRestController {

	@Autowired
	private AnimalService animalService;

	@ApiOperation(nickname = "teste", value = "Retorna uma lista de Animais")
	@GetMapping("/read-all")
	public ResponseEntity<List<Animal>> readAll() {

		return ResponseEntity.ok(animalService.readAll());

	}

	@ApiOperation(value = "Retorna um animal baseado no ID")
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Animal> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(animalService.readById(id));

	}

	@ApiOperation(value = "Cria uma novo animal", consumes = "application/json")
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

}
