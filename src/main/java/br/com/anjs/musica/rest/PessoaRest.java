package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.MusicaDTO;
import br.com.anjs.musica.dto.PessoaDTO;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.service.MusicaService;
import br.com.anjs.musica.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaRest {

    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity criar(PessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(DTOFactory.modelToDTO(service.save(ModelFactory.DTOToModel(dto))));
    }

    @PutMapping
    public ResponseEntity editar(PessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(DTOFactory.modelToDTO(service.edit(ModelFactory.DTOToModel(dto))));
    }

    @DeleteMapping
    public ResponseEntity deletar(PessoaDTO dto) {

        service.delete(service.findByUUID(dto.uuid));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
