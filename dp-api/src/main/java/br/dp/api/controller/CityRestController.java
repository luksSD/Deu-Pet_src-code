package br.dp.api.controller;

import br.dp.api.service.CityService;
import br.dp.model.Municipio;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/city")
@Api(value = "City", tags = "Município")
@CrossOrigin(origins = "*")
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/read-by-id/{id}")
    @ApiOperation(value = "Retorna um município selecionado pelo ID")
    public ResponseEntity<Municipio> readById(@PathVariable("id") final long id) {

        return ResponseEntity.ok(cityService.readById(id));

    }

}
