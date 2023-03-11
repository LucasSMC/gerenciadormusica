package br.com.anjs.musica.dto.playlist;

import br.com.anjs.musica.dto.IDTOGet;
import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.pessoa.PessoaDTO;

import java.util.List;
import java.util.Set;

public class PlaylistDTO implements IDTOGet {

    public String uuid;
    public PessoaDTO dono;
    public Set<MusicaDTO> musicas;
}
