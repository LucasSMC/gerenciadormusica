package br.com.anjs.musica.factory;

import br.com.anjs.musica.dto.GeneroDTO;
import br.com.anjs.musica.dto.MusicaDTO;
import br.com.anjs.musica.dto.PessoaDTO;
import br.com.anjs.musica.dto.PlaylistDTO;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;

public interface IDTOFactory {

    MusicaDTO modelToDTO(Musica model);
    PessoaDTO modelToDTO(Pessoa model);
    GeneroDTO modelToDTO(Genero model);
    PlaylistDTO modelToDTO(Playlist model);

}
