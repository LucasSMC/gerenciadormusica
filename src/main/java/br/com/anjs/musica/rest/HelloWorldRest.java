package br.com.anjs.musica.rest;

import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class HelloWorldRest {

    @Autowired
    private MusicaRepository repository;

    @GetMapping
    public ResponseEntity HelloWorld(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

}
