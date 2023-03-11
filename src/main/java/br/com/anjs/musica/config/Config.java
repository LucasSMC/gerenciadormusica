package br.com.anjs.musica.config;

import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Configuration
public class Config {
    @Autowired
    MusicaRepository repository;

    @PostConstruct
    public void aosejaosej() {
        Musica musica = new Musica();
        musica.setAlbum("Meteora");
        musica.setArtista("Linkin Park");
        musica.setNome("Numb");
        musica.setUuid(UUID.randomUUID());
        System.out.println(musica);
        repository.saveAndFlush(musica);
    }
}
