package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.playlist.PostPlaylistDTO;
import br.com.anjs.musica.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/playlist")
public class PlaylistRest {
    @Autowired
    private PlaylistService service;

    @Operation(summary = "Retorna todas as Playlists em base de dados")
    @GetMapping(produces = "application/json")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity criar(PostPlaylistDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity editar(@PathVariable("uuid") String uuid, @RequestBody PostPlaylistDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable("uuid") String uuid) {

        service.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
