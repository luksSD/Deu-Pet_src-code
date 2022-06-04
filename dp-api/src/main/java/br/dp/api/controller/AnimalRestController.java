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

@RestController
@RequestMapping("/api/v1/animal")
@CrossOrigin(origins = "*")
public class AnimalRestController {

	@Autowired
	private AnimalService animalService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Animal>> readAll() {

		return ResponseEntity.ok(animalService.readAll());

	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Animal> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(animalService.readById(id));

	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody final Animal animal) {

		return ResponseEntity.ok(animalService.create(animal));

	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Animal animal) {

		return ResponseEntity.ok(animalService.update(animal));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

		return ResponseEntity.ok(animalService.delete(id));

	}

}
