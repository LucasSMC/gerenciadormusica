package br.com.anjs.musica.service;

import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.repository.MusicaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MusicaService extends AbstractService<Musica, MusicaRepository> {

    @Override
    public Musica findByUUID(UUID uuid) {
        return super.repository.findByUUID(uuid);
    }
}
