package br.com.anjs.musica.factory;

import br.com.anjs.musica.dto.*;
import br.com.anjs.musica.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DTOFactory implements IDTOFactory {

    public IDTO modelToDTO(IModel model) {
        return null;
    }
    public MusicaDTO modelToDTO(Musica model) {
        MusicaDTO dto = new MusicaDTO();
        dto.album = model.getAlbum();
        dto.nome = model.getNome();
        dto.artista = model.getArtista();
        dto.uuid = model.getUuid();
        return dto;
    }

    public PessoaDTO modelToDTO(Pessoa model) {
        PessoaDTO dto = new PessoaDTO();
        dto.nome = model.getNome();
        dto.uuid = model.getUuid();
        return dto;
    }

    public PlaylistDTO modelToDTO(Playlist model) {
        PlaylistDTO dto = new PlaylistDTO();
        dto.uuid = model.getUuid();
        dto.dono = modelToDTO(model.getDono());
        dto.musicas = model.getMusicas().stream().map(this::modelToDTO).collect(Collectors.toList());
        return dto;
    }

    public GeneroDTO modelToDTO(Genero model) {
        GeneroDTO dto = new GeneroDTO();
        dto.nome = model.getNome();
        dto.uuid = model.getUuid();
        return dto;
    }

}
