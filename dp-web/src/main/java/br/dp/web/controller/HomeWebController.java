package br.dp.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/health-heck")
    public ResponseEntity<Boolean> healthCheck() {
        return ResponseEntity.ok(true);
    }

}
