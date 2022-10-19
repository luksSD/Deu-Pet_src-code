package br.dp.api.controller;

import br.dp.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestHeader("Authorization") final String encodedData) {

        System.out.println("Chegou request com base64: " + encodedData);

        return ResponseEntity.ok().build();

    }


}
