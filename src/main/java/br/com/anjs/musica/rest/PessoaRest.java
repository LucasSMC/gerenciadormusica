package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.pessoa.PostPessoaDTO;
import br.com.anjs.musica.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaRest {

    @Autowired
    private PessoaService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity criar(PostPessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity editar(@PathVariable("uuid") String uuid, @RequestBody PostPessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable("uuid") String uuid) {

        service.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
