package br.com.anjs.musica.factory;

import br.com.anjs.musica.dto.GeneroDTO;
import br.com.anjs.musica.dto.MusicaDTO;
import br.com.anjs.musica.dto.PessoaDTO;
import br.com.anjs.musica.dto.PlaylistDTO;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ModelFactory implements IModelFactory {


    public Playlist DTOToModel(PlaylistDTO dto) {
        Playlist model = new Playlist();
        model.setDono(DTOToModel(dto.dono));
        model.setUuid(dto.uuid);
        model.setMusicas(dto.musicas.stream().map(ModelFactory::DTOToModel).collect(Collectors.toList()));
        return model;
    }

    public Musica DTOToModel(MusicaDTO dto) {
        Musica model = new Musica();
        model.setNome(dto.nome);
        model.setArtista(dto.artista);
        model.setAlbum(dto.album);
        model.setUuid(dto.uuid);
        return model;
    }

    public Pessoa DTOToModel(PessoaDTO dto) {
        Pessoa model = new Pessoa();
        model.setUuid(dto.uuid);
        model.setNome(dto.nome);
        return model;
    }

    public Genero DTOToModel(GeneroDTO dto) {
        Genero model = new Genero();
        model.setUuid(dto.uuid);
        model.setNome(dto.nome);
        return model;
    }
}
