package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.genero.GeneroDTO;
import br.com.anjs.musica.dto.genero.PostGeneroDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ExceptionFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GeneroService implements ICrudService<GeneroDTO, PostGeneroDTO>{

    @Autowired
    private GeneroRepository repository;

    @Override
    public GeneroDTO save(PostGeneroDTO generoDTO) {
        Genero model = ModelFactory.DTOToModel(generoDTO);
        model.setUuid(UUID.randomUUID().toString());
        repository.save(model);
        return DTOFactory.modelToDTO(model);

    }

    @Override
    public GeneroDTO edit(String uuid, PostGeneroDTO dto) throws EntidadeNaoEncontrada {
        Optional<Genero> optional = Optional.of(repository.findByUUID(uuid));
        Genero model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Genero não encontrada"));
        repository.save(model);
        return DTOFactory.modelToDTO(model);
    }

    @Override
    public List<GeneroDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());

    }

    @Override
    public void delete(String uuid) throws EntidadeNaoEncontrada {

        Optional<Genero> model = Optional.of(repository.findByUUID(uuid));

        repository.delete(model.orElseThrow(() -> ExceptionFactory.naoEncontrada("Genero não encontrada")));

    }

    @Override
    public GeneroDTO findByUUID(String uuid) throws EntidadeNaoEncontrada {
        Optional<Genero> optional = Optional.of(repository.findByUUID(uuid));
        Genero model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Genero não encontrada"));
        return DTOFactory.modelToDTO(model);
    }


}
