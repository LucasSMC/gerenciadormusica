package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.musica.PostMusicaDTO;
import br.com.anjs.musica.service.MusicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/musicas")
public class MusicaRest {

    @Autowired
    private MusicaService service;

    //@ApiResponses(value = { @ApiResponse(description = "successful operation",content = { @Content(examples = @ExampleObject(name = "500", ref = "#/components/examples/http500Example"), mediaType = "application/json", schema = @Schema(implementation = User.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = User.class)) }) })
    @GetMapping(produces = "application/json")
    @Operation(summary = "Retorna todas as Musicas em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = @ExampleObject(name = "200", description = "Sucesso"),
                            schema = @Schema(implementation = MusicaDTO.class))
            }
    )
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody PostMusicaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity editar(@PathVariable("uuid") String uuid, @RequestBody PostMusicaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable("uuid") String uuid) {

        service.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
