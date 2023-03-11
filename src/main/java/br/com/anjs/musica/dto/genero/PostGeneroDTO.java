package br.com.anjs.musica.dto.genero;

import br.com.anjs.musica.dto.IDTOPost;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PostGeneroDTO implements IDTOPost {

    @NotBlank(message = "Nome é Obrigatório")
    @NotEmpty(message = "Nome da Música é Obrigatório")
    public String nome;
}
