package br.com.anjs.musica.factory;

import br.com.anjs.musica.dto.genero.PostGeneroDTO;
import br.com.anjs.musica.dto.musica.PostMusicaDTO;
import br.com.anjs.musica.dto.pessoa.PostPessoaDTO;
import br.com.anjs.musica.dto.playlist.PostPlaylistDTO;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;
import org.springframework.stereotype.Component;

@Component
public class ModelFactory{

    public static Playlist DTOToModel(PostPlaylistDTO dto) {
        Playlist model = new Playlist();
        model.setDono(DTOToModel(dto.dono));
        return model;
    }

    public static Musica DTOToModel(PostMusicaDTO dto) {
        Musica model = new Musica();
        model.setNome(dto.nome);
        model.setArtista(dto.artista);
        model.setAlbum(dto.album);
        return model;
    }

    public static Pessoa DTOToModel(PostPessoaDTO dto) {
        Pessoa model = new Pessoa();
        model.setNome(dto.nome);
        return model;
    }

    public static Genero DTOToModel(PostGeneroDTO dto) {
        Genero model = new Genero();
        model.setNome(dto.nome);
        return model;
    }
}
