package br.dp.api.controller;

import br.dp.api.service.InstituicaoService;
import br.dp.model.Instituicao;
import br.dp.model.UsersArquives;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instituicao")
@Api(value = "Instituicao", tags = "Instituição")
@CrossOrigin(origins = "*")
public class InstituicaoRestController {

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/read-all")
    @ApiOperation(value = "Retorna lista de instituiçoes")
    public ResponseEntity<List<Instituicao>> readAll() {

        return ResponseEntity.ok(instituicaoService.readAll());

    }

    @GetMapping("/read-by-id/{id}")
    @ApiOperation(value = "Retorna instituição selecionada pelo ID")
    public ResponseEntity<Instituicao> readById(@PathVariable("id") final long id) {

        return ResponseEntity.ok(instituicaoService.readById(id));

    }

    @PostMapping("/create")
    @ApiOperation(value = "Cria cadastro de Instituição")
    public ResponseEntity<Long> create(@RequestBody final Instituicao instituicao) {

        return ResponseEntity.ok(instituicaoService.create(instituicao));

    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualiza cadastro de Instituiçoes")
    public ResponseEntity<Boolean> update(@RequestBody final Instituicao instituicao) {

        return ResponseEntity.ok(instituicaoService.update(instituicao));

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta cadastro de Instituiçoes")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

        return ResponseEntity.ok(instituicaoService.delete(id));

    }

    @ApiOperation(value = "Salva o caminho da imagen relacionadas a instituição", consumes = "application/json")
    @PostMapping("/save-image")
    public ResponseEntity<Long> saveFileAttributes(@RequestBody final UsersArquives imagePath) {

        return ResponseEntity.ok(instituicaoService.saveFileAttributes(imagePath));

    }

}
