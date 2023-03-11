package br.com.anjs.musica.dto.musica;

import br.com.anjs.musica.dto.IDTOGet;
import com.fasterxml.jackson.annotation.JsonInclude;

public class MusicaDTO implements IDTOGet {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String uuid;

    public String nome;

    public String artista;

    public String album;

}

