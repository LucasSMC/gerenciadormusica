package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.genero.GeneroDTO;
import br.com.anjs.musica.dto.genero.PostGeneroDTO;
import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.service.GeneroService;
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
@RequestMapping(path = "/generos",produces = "application/json")
@Tag(name = "Genero Musical", description = "Endpoints para Generos Musicais")
public class GeneroRest {

    @Autowired
    private GeneroService service;



    @Operation(summary = "Retorna todas os Generos em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = @ExampleObject(name = "200", description = "Sucesso"),
                            schema = @Schema(implementation = MusicaDTO.class)),
            }
    )
    @GetMapping
    public ResponseEntity getAll() {

        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @Operation(summary = "Cria um Genero em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = GeneroDTO.class))

            }
    )
    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody PostGeneroDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }

    @Operation(summary = "Altera um Genero específico em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = GeneroDTO.class))

            }
    )
    @PutMapping("/<uuid>")
    public ResponseEntity editar(@PathVariable("uuid") String uuid, @Valid @RequestBody PostGeneroDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @Operation(summary = "Deleta um gênero de base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
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
