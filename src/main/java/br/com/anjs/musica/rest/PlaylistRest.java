package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.service.PlaylistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/playlists")
@Tag(name = "Playlists", description = "Endpoints da API de Playlists")
public class PlaylistRest {
    @Autowired
    private PlaylistService service;

    @Operation(summary = "Retorna todas as Playlists em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Pessoa não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = PlaylistDTO.class))

            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping(value = "/{uuid}", produces = "application/json")
    @Operation(summary = "Cria uma Playlist pra pessoa informada")
    @ApiResponse(responseCode = "201", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "201", description = "Sucesso"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
    public ResponseEntity criar(@PathVariable("uuid") String uuidDono) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criarPlaylist(uuidDono));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{uuid}", produces = "application/json")
    @Operation(summary = "Deleta uma Playlist da Base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")}
                            )

            }
    )
    public ResponseEntity deletar(@PathVariable("uuid") String uuid) {

        try {
            service.delete(uuid);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
