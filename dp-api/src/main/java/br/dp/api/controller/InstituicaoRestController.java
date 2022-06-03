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

import br.dp.api.service.InstituicaoService;
import br.dp.model.Instituicao;

@RestController
@RequestMapping("/api/v1/instituicao")
@CrossOrigin(origins = "*")
public class InstituicaoRestController {

	@Autowired
	private InstituicaoService instituicaoService;

	@GetMapping("/read-all")
	public ResponseEntity<List<Instituicao>> readAll() {

		return ResponseEntity.ok(instituicaoService.readAll());

	}

	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Instituicao> readById(@PathVariable("id") final long id) {

		return ResponseEntity.ok(instituicaoService.readById(null));

	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody final Instituicao instituicao) {

		return ResponseEntity.ok(instituicaoService.create(instituicao));

	}

	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody final Instituicao instituicao) {

		return ResponseEntity.ok(instituicaoService.update(instituicao));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

		return ResponseEntity.ok(instituicaoService.delete(null));

	}

}
