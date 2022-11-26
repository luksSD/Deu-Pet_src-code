package br.dp.api.controller;

import br.dp.api.service.UserService;
import br.dp.model.UsersArquives;
import br.dp.model.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Api(value = "User", tags = "Usuário")
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Valida acesso de um usuário")
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestHeader("Authorization") final String encodedData) {

        System.out.println("Chegou request com base64: " + encodedData);

        final Usuario user = userService.validateLogin(encodedData);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);

    }

    @ApiOperation(value = "Cria novo usuário")
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody final Usuario user) {

        return ResponseEntity.ok(userService.create(user));

    }

    @ApiOperation(value = "Altera senha do usuário")
    @PostMapping("/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody final Usuario user) {

        return ResponseEntity.ok(userService.changePassword(user));

    }



}
