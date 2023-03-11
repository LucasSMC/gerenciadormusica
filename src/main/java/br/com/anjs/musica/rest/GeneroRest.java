package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.GeneroDTO;
import br.com.anjs.musica.dto.MusicaDTO;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.service.GeneroService;
import br.com.anjs.musica.service.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pessoas")
public class GeneroRest {

    @Autowired
    private GeneroService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll().stream().map(DTOFactory::modelToDTO));
    }

    @PostMapping
    public ResponseEntity criar(GeneroDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(DTOFactory.modelToDTO(service.save(ModelFactory.DTOToModel(dto))));
    }

    @PutMapping
    public ResponseEntity editar(GeneroDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(DTOFactory.modelToDTO(service.edit(ModelFactory.DTOToModel(dto))));
    }

    @DeleteMapping
    public ResponseEntity deletar(GeneroDTO dto) {

        service.delete(service.findByUUID(dto.uuid));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
