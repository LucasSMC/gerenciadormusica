package br.com.anjs.musica.dto.operacao;

import br.com.anjs.musica.dto.IDTOPost;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CurtirMusicaDTO implements IDTOPost {
    @NotBlank(message = "UUID da Pessoa não pode estar em branco")
    @NotEmpty(message = "UUID da Pessoa não pode estar em branco")
    public String uuidPessoa;
    @NotBlank(message = "UUID da Música não pode estar em branco")
    @NotEmpty(message = "UUID da Música não pode estar em branco")
    public String uuidMusica;

}
