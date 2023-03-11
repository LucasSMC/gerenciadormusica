package br.com.anjs.musica.factory;

import br.com.anjs.musica.dto.genero.GeneroDTO;
import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.pessoa.PessoaDTO;
import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOFactory{

    public static MusicaDTO modelToDTO(Musica model) {
        MusicaDTO dto = new MusicaDTO();
        dto.album = model.getAlbum();
        dto.nome = model.getNome();
        dto.artista = model.getArtista();
        dto.uuid = model.getUuid().toString();
        if(model.getLikes()!=null) {
            dto.likes = model.getLikes().stream().count();
        }
        return dto;
    }

    public static PessoaDTO modelToDTO(Pessoa model) {
        PessoaDTO dto = new PessoaDTO();
        dto.nome = model.getNome();
        dto.uuid = model.getUuid().toString();
        return dto;
    }

    public static PlaylistDTO modelToDTO(Playlist model) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.uuid = model.getUuid().toString();
        dto.dono = modelToDTO(model.getDono());
        dto.musicas = model.getMusicas().stream().map(DTOFactory::modelToDTO).collect(Collectors.toSet());
        return dto;
    }

    public static GeneroDTO modelToDTO(Genero model) {
        GeneroDTO dto = new GeneroDTO();
        dto.nome = model.getNome();
        dto.uuid = model.getUuid().toString();
        return dto;
    }

}
