package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.operacao.AdicionarMusicaPlaylistDTO;
import br.com.anjs.musica.dto.operacao.CurtirMusicaDTO;
import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.factory.ExceptionFactory;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;
import br.com.anjs.musica.repository.MusicaRepository;
import br.com.anjs.musica.repository.PessoaRepository;
import br.com.anjs.musica.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperacoesService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;



    public PlaylistDTO adicionarMusicaPlaylist(AdicionarMusicaPlaylistDTO dto) throws EntidadeNaoEncontrada {
        Playlist playlist = playlistRepository.findByUUID(dto.uuidPlaylist);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);
        if(playlist==null) {
           throw ExceptionFactory.naoEncontrada("Playlist não encontrada");
        }
        if (musica==null) {
           throw ExceptionFactory.naoEncontrada("Música não encontrada");
        }

        playlist.getMusicas().add(musica);
        playlistRepository.save(playlist);
        return DTOFactory.modelToDTO(playlist);
    }

    public PlaylistDTO removerMusicaPlaylist(AdicionarMusicaPlaylistDTO dto) throws EntidadeNaoEncontrada {
        Playlist playlist = playlistRepository.findByUUID(dto.uuidPlaylist);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        if(playlist==null) {
            throw ExceptionFactory.naoEncontrada("Playlist não encontrada");
        }
        if (musica==null) {
            throw ExceptionFactory.naoEncontrada("Música não encontrada");
        }

        playlist.getMusicas().remove(musica);
        playlistRepository.save(playlist);
        return DTOFactory.modelToDTO(playlist);
    }

    public MusicaDTO curtirMusica(CurtirMusicaDTO dto) throws EntidadeNaoEncontrada {
        Pessoa pessoa = pessoaRepository.findByUUID(dto.uuidPessoa);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        if(pessoa==null) {
            throw ExceptionFactory.naoEncontrada("Pessoa não encontrada");
        }
        if (musica==null) {
            throw ExceptionFactory.naoEncontrada("Música não encontrada");
        }
        pessoa.getLikes().add(musica);
        musica.getLikes().add(pessoa);

        pessoaRepository.save(pessoa);
        musicaRepository.save(musica);

        return DTOFactory.modelToDTO(musica);
    }

    public MusicaDTO descurtirMusica(CurtirMusicaDTO dto) throws EntidadeNaoEncontrada {
        Pessoa pessoa = pessoaRepository.findByUUID(dto.uuidPessoa);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        if(pessoa==null) {
            throw ExceptionFactory.naoEncontrada("Pessoa não encontrada");
        }
        if (musica==null) {
            throw ExceptionFactory.naoEncontrada("Música não encontrada");
        }

        pessoa.getLikes().remove(musica);
        musica.getLikes().remove(pessoa);

        pessoaRepository.save(pessoa);
        musicaRepository.save(musica);

        return DTOFactory.modelToDTO(musica);
    }
}
