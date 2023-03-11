package br.com.anjs.musica.dto.playlist;

import br.com.anjs.musica.dto.IDTOPost;
import br.com.anjs.musica.dto.pessoa.PostPessoaDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PostPlaylistDTO implements IDTOPost {
    @NotBlank(message = "UUID da playlist não pode estar em branco")
    @NotEmpty(message = "UUID da playlist não pode estar em branco")
    public PostPessoaDTO dono;

}
