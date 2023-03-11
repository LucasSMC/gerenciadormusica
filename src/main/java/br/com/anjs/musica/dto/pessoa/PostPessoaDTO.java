package br.com.anjs.musica.dto.pessoa;

import br.com.anjs.musica.dto.IDTOPost;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PostPessoaDTO implements IDTOPost {
    @NotBlank(message = "A pessoa Precisa ter um nome")
    @NotEmpty(message = "A pessoa Precisa ter um nome")
    public String nome;
}
