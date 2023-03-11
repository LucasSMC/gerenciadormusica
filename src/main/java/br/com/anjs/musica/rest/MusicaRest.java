package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.genero.GeneroDTO;
import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.musica.PostMusicaDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.service.MusicaService;
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

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/musicas",produces = "application/json")
@Tag(name = "Musica", description = "Endpoints para Crud de Músicasa")
public class MusicaRest {

    @Autowired
    private MusicaService service;


    @GetMapping
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

    @Operation(summary = "Cria uma musica em Base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = MusicaDTO.class))
            }
    )
    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody PostMusicaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }


    @Operation(summary = "Altera Musica em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Musica não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = MusicaDTO.class))

            }
    )
    @PutMapping("/{uuid}")
    public ResponseEntity editar(@PathVariable("uuid") String uuid, @Valid  @RequestBody PostMusicaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
        } catch (EntidadeNaoEncontrada e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @Operation(summary = "Deleta uma Música em base de dados")
    @ApiResponse(responseCode = "200", description = "Deletado", content =
            {
                    @Content(examples = {
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Musica não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")
                            })})
    @DeleteMapping("/{uuid}")
    public ResponseEntity deletar(@PathVariable("uuid") String uuid) {

        try {
            service.delete(uuid);
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
