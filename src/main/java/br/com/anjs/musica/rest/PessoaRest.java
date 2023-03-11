package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.pessoa.PessoaDTO;
import br.com.anjs.musica.dto.pessoa.PostPessoaDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.service.PessoaService;
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
@RequestMapping(path = "/pessoas",produces = "application/json")
@Tag(name = "Pessoas",description = "Endpoints da API de Pessoas")
public class PessoaRest {

    @Autowired
    private PessoaService service;

    @Operation(summary = "Retorna todas as Pessoas em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso")},
                            schema = @Schema(implementation = PessoaDTO.class))

            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }



    @Operation(summary = "Cria uma Pessoa em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = PessoaDTO.class))

            }
    )
    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody PostPessoaDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
    }

    @Operation(summary = "Altera uma Pessoa em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = PessoaDTO.class))

            }
    )
    @PutMapping("/{uuid}")
    public ResponseEntity editar(@PathVariable("uuid") String uuid,@Valid @RequestBody PostPessoaDTO dto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.edit(uuid, dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Cria uma Pessoa em base de dados")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
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

    @Operation(summary = "Retorna todas as playlists de uma determinada Pessoa")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Genero não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")},
                            schema = @Schema(implementation = PessoaDTO.class))

            }
    )
    @GetMapping(value = "/{uuid}/playlists", produces = "application/json")
    public ResponseEntity getByDono(@PathVariable("uuid") String uuidDono ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPlaylists(uuidDono));

    }
}
