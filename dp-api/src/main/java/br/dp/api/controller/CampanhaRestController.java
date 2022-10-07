package br.dp.api.controller;

import br.dp.api.service.CampanhaService;
import br.dp.model.Campanha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/campanha")
@CrossOrigin(origins = "*")
public class CampanhaRestController {

    @Autowired
    private CampanhaService service;

    @GetMapping("/read-all")
    public ResponseEntity<List<Campanha>> readAll() {
        return ResponseEntity.ok(service.readAll());
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Campanha> readById(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.readById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> update(@RequestBody final Campanha campanha) {
        return ResponseEntity.ok(service.update(campanha));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody final Campanha campanha) {
        return ResponseEntity.ok(service.create(campanha));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
