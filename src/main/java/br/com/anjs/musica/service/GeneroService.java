package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.genero.GeneroDTO;
import br.com.anjs.musica.dto.genero.PostGeneroDTO;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public GeneroDTO edit(String uuid, PostGeneroDTO dto) {
        Genero model = repository.findByUUID(uuid);
        if(!dto.nome.isEmpty() && !dto.nome.isBlank()) {
            model.setNome(dto.nome);
        }
        repository.save(model);
        return DTOFactory.modelToDTO(model);
    }

    @Override
    public List<GeneroDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());

    }

    @Override
    public void delete(String uuid) {

        Genero model = repository.findByUUID(uuid);
        repository.delete(model);

    }

    @Override
    public GeneroDTO findByUUID(String uuid){

        return DTOFactory.modelToDTO(repository.findByUUID(uuid));
    }


}
