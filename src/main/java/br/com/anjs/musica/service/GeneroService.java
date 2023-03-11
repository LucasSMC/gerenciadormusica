package br.com.anjs.musica.service;

import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.repository.GeneroRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GeneroService extends AbstractService<Genero, GeneroRepository> {

    @Override
    public Genero findByUUID(UUID uuid){
        return super.repository.findByUUID(uuid);
    }


}
