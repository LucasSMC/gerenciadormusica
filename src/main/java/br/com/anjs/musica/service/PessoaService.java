package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.pessoa.PessoaDTO;
import br.com.anjs.musica.dto.pessoa.PostPessoaDTO;
import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ExceptionFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;
import br.com.anjs.musica.repository.PessoaRepository;
import br.com.anjs.musica.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PessoaService implements ICrudService<PessoaDTO, PostPessoaDTO> {

    @Autowired
    private PessoaRepository repository;

    @Autowired
    private PlaylistRepository playlistRepository;


    @Override
    public PessoaDTO save(PostPessoaDTO dto) {
        Pessoa model = ModelFactory.DTOToModel(dto);
        model.setUuid(UUID.randomUUID().toString());
        repository.save(model);
        return DTOFactory.modelToDTO(model);
    }

    @Override
    public PessoaDTO edit(String uuid, PostPessoaDTO dto) throws EntidadeNaoEncontrada {

        Optional<Pessoa> optional = Optional.of(repository.findByUUID(uuid));
        Pessoa model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Pessoa não encontrada"));
        Pessoa novo = ModelFactory.DTOToModel(dto);
        novo.setUuid(model.getUuid());
        novo.setId(model.getId());
        repository.save(novo);
        return DTOFactory.modelToDTO(novo);
    }

    @Override
    public List<PessoaDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());
    }

    @Override
    public void delete(String uuid) throws EntidadeNaoEncontrada {

        Optional<Pessoa> optional = Optional.of(repository.findByUUID(uuid));
        Pessoa model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Pessoa não encontrada"));
        repository.delete(model);

    }

    @Override
    public PessoaDTO findByUUID(String uuid) throws EntidadeNaoEncontrada {

        Optional<Pessoa> optional = Optional.of(repository.findByUUID(uuid));
        Pessoa model = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Pessoa não encontrada"));
        return DTOFactory.modelToDTO(model);
    }

    public Object getPlaylists(String uuidDono) {

        List<Playlist> playlists = playlistRepository.findByDono(uuidDono);
        return playlists.stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());
    }
}
