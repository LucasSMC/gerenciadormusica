package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ExceptionFactory;
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
public class PlaylistService{

    @Autowired
    private PlaylistRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PlaylistDTO> findAll() {
        return repository.findAll().stream().map(DTOFactory::modelToDTO).collect(Collectors.toList());
    }


    public void delete(String uuid) throws EntidadeNaoEncontrada {

        Optional<Playlist> model = Optional.of(repository.findByUUID(uuid));
        repository.delete(model.orElseThrow(() -> ExceptionFactory.naoEncontrada("Playlist não encontrada")));
    }


    public PlaylistDTO findByUUID(String uuid) throws EntidadeNaoEncontrada {


        Optional<Playlist> model = Optional.of(repository.findByUUID(uuid));
        return DTOFactory.modelToDTO(model.orElseThrow(() -> ExceptionFactory.naoEncontrada("Playlist não encontrada")));
    }

    public PlaylistDTO criarPlaylist(String uuidDono) throws EntidadeNaoEncontrada {
        Optional<Pessoa> optional = Optional.of(pessoaRepository.findByUUID(uuidDono));
        Pessoa dono = optional.orElseThrow(() -> ExceptionFactory.naoEncontrada("Pessoa não encontrada"));
        Playlist model = new Playlist();
        model.setDono(dono);
        model.setUuid(UUID.randomUUID().toString());
        repository.save(model);
        return DTOFactory.modelToDTO(model);

    }



}
