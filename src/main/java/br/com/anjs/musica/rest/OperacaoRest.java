package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.operacao.AdicionarMusicaPlaylistDTO;
import br.com.anjs.musica.dto.operacao.CurtirMusicaDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.service.OperacoesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Tag(name = "Operações", description = "Endpoints da API de Operações")
public class OperacaoRest {

    @Autowired
    private OperacoesService service;

    @PostMapping("/playlists/adicionar")
    @Operation(summary = "Adiciona uma musica a uma playlist")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Alguma Entidade não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
    public ResponseEntity adicionarMusica(@RequestBody AdicionarMusicaPlaylistDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionarMusicaPlaylist(dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/playlists/remover")
    @Operation(summary = "Remove uma musica de uma playlist")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Alguma Entidade não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
    public ResponseEntity removerMusica(@RequestBody AdicionarMusicaPlaylistDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.removerMusicaPlaylist(dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/musicas/curtir")
    @Operation(summary = "Curte uma Música")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Alguma Entidade não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
    public ResponseEntity curtirMusica(@RequestBody CurtirMusicaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.curtirMusica(dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/musicas/descurtir")
    @Operation(summary = "Descurte uma Música")
    @ApiResponse(responseCode = "200", description = "Sucesso", content =
            {
                    @Content(examples = {@ExampleObject(name = "200", description = "Sucesso"),
                            @ExampleObject(name = "404", description = "Alguma Entidade não encontrado"),
                            @ExampleObject(name = "400", description = "Campo inválido")})


            }
    )
    public ResponseEntity descurtirMusica(@RequestBody CurtirMusicaDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.descurtirMusica(dto));
        } catch (EntidadeNaoEncontrada e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
