package br.com.anjs.musica.dto;

import java.util.List;
import java.util.UUID;

public class PlaylistDTO {

    public UUID uuid;
    public PessoaDTO dono;
    public List<MusicaDTO> musicas;
}
