package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.musica.PostMusicaDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ExceptionFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class MusicaService implements ICrudService<MusicaDTO, PostMusicaDTO> {

    @Autowired
    private MusicaRepository repository;


    @Override
    public MusicaDTO save(PostMusicaDTO dto) {

        Musica model = ModelFactory.DTOToModel(dto);
        model.setUuid(UUID.randomUUID().toString());
        repository.save(model);

        return DTOFactory.modelToDTO(model);
    }

    @Override
    public MusicaDTO edit(String uuid, PostMusicaDTO dto) throws EntidadeNaoEncontrada {
        Optional<Musica> optional = Optional.of(repository.findByUUID(uuid));
        Musica model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Música não encontrada"));
        Musica novo = ModelFactory.DTOToModel(dto);
        novo.setId(model.getId());
        novo.setUuid(model.getUuid());
        repository.save(novo);
        return DTOFactory.modelToDTO(novo);
    }

    @Override
    public List<MusicaDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(String uuid) throws EntidadeNaoEncontrada {

        Optional<Musica> model = Optional.of(repository.findByUUID(uuid));

        repository.delete(model.orElseThrow(() -> ExceptionFactory.naoEncontrada("Musica Invalida")));
    }

    @Override
    public MusicaDTO findByUUID(String uuid) throws EntidadeNaoEncontrada {
        Optional<Musica> optional = Optional.of(repository.findByUUID(uuid));
        Musica model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Música não encontrada"));
        return DTOFactory.modelToDTO(model);
    }
}
