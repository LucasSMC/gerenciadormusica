package br.com.anjs.musica.dto.musica;

import br.com.anjs.musica.dto.IDTOPost;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class PostMusicaDTO  implements IDTOPost {

    @NotBlank(message = "Nome da Música é Obrigatório")
    @NotEmpty(message = "Nome da Música é Obrigatório")
    public String nome;

    @NotBlank(message = " Artista da Música é Obrigatório")
    @NotEmpty(message = "Artista da Música é Obrigatório")
    public String artista;

    @NotBlank(message = "Album da Música é Obrigatório")
    @NotEmpty(message = "Album da Música é Obrigatório")
    public String album;
}
