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

import br.dp.api.service.PessoaService;
import br.dp.model.Pessoa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/pessoa")
@Api(value = "Pessoa", tags = "Pessoa")
@CrossOrigin(origins = "*")
public class PessoaRestController {


    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/read-all")
    @ApiOperation(value = "Retorna lista de pessoas")
    public ResponseEntity<List<Pessoa>> readAll() {

        return ResponseEntity.ok(pessoaService.readAll());

    }

    @GetMapping("/read-by-id/{id}")
    @ApiOperation(value = "Retorna pessoa selecionada pelo ID")
    public ResponseEntity<Pessoa> readById(@PathVariable("id") final long id) {

        return ResponseEntity.ok(pessoaService.readById(id));

    }

    @PostMapping("/create")
    @ApiOperation(value = "Cria cadastro de Pessoa")
    public ResponseEntity<Long> create(@RequestBody final Pessoa pessoa) {

        return ResponseEntity.ok(pessoaService.create(pessoa));

    }

    @PutMapping("/update")
    @ApiOperation(value = "Atualiza cadastro de Pessoas")
    public ResponseEntity<Boolean> update(@RequestBody final Pessoa pessoa) {

        return ResponseEntity.ok(pessoaService.update(pessoa));

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Deleta cadastro de Pessoas")
    public ResponseEntity<Boolean> delete(@PathVariable("id") final long id) {

        return ResponseEntity.ok(pessoaService.delete(id));

    }
}
