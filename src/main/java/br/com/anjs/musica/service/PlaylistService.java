package br.com.anjs.musica.service;

import br.com.anjs.musica.model.Playlist;
import br.com.anjs.musica.repository.PlaylistRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PlaylistService extends AbstractService<Playlist, PlaylistRepository> {
    @Override
    public Playlist findByUUID(UUID uuid) {
        return super.repository.findByUUID(uuid);
    }
}
