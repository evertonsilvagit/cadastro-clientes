package br.com.hitss.cadastro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/health")
@RestController
public class HealthController {

    @GetMapping
    public ResponseEntity<?> check(){
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
