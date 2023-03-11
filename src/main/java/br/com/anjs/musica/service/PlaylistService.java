package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.dto.playlist.PostPlaylistDTO;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Playlist;
import br.com.anjs.musica.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PlaylistService implements ICrudService<PlaylistDTO, PostPlaylistDTO> {

    @Autowired
    private PlaylistRepository repository;


    @Override
    public PlaylistDTO save(PostPlaylistDTO dto) {
        Playlist model = ModelFactory.DTOToModel(dto);
        model.setUuid(UUID.randomUUID().toString());
        repository.save(model);
        return DTOFactory.modelToDTO(model);

    }

    @Override
    public PlaylistDTO edit(String uuid, PostPlaylistDTO dto) {
        Playlist model = repository.findByUUID(uuid);
        Playlist novo = ModelFactory.DTOToModel(dto);
        novo.setId(model.getId());
        novo.setUuid(model.getUuid());
        repository.save(novo);
        return DTOFactory.modelToDTO(novo);
    }

    @Override
    public List<PlaylistDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(String uuid) {

        Playlist model = repository.findByUUID(uuid);
        repository.delete(model);
    }

    @Override
    public PlaylistDTO findByUUID(String uuid) {

        Playlist model = repository.findByUUID(uuid);
        return DTOFactory.modelToDTO(model);
    }
}
