package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.musica.MusicaDTO;
import br.com.anjs.musica.dto.operacao.AdicionarMusicaPlaylistDTO;
import br.com.anjs.musica.dto.operacao.CurtirMusicaDTO;
import br.com.anjs.musica.dto.playlist.PlaylistDTO;
import br.com.anjs.musica.factory.DTOFactory;
import br.com.anjs.musica.model.Musica;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.model.Playlist;
import br.com.anjs.musica.repository.MusicaRepository;
import br.com.anjs.musica.repository.PessoaRepository;
import br.com.anjs.musica.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OperacoesService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicaRepository musicaRepository;



    public PlaylistDTO adicionarMusicaPlaylist(AdicionarMusicaPlaylistDTO dto) {
        Playlist playlist = playlistRepository.findByUUID(dto.uuidPlaylist);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        playlist.getMusicas().add(musica);
        playlistRepository.save(playlist);
        return DTOFactory.modelToDTO(playlist);
    }

    public PlaylistDTO removerMusicaPlaylist(AdicionarMusicaPlaylistDTO dto) {
        Playlist playlist = playlistRepository.findByUUID(dto.uuidPlaylist);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        playlist.getMusicas().remove(musica);
        playlistRepository.save(playlist);
        return DTOFactory.modelToDTO(playlist);
    }

    public MusicaDTO curtirMusica(CurtirMusicaDTO dto) {
        Pessoa pessoa = pessoaRepository.findByUUID(dto.uuidPessoa);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        pessoa.getLikes().add(musica);
        musica.getLikes().add(pessoa);

        pessoaRepository.save(pessoa);
        musicaRepository.save(musica);

        return DTOFactory.modelToDTO(musica);
    }

    public MusicaDTO descurtirMusica(CurtirMusicaDTO dto) {
        Pessoa pessoa = pessoaRepository.findByUUID(dto.uuidPessoa);
        Musica musica = musicaRepository.findByUUID(dto.uuidMusica);

        pessoa.getLikes().remove(musica);
        musica.getLikes().remove(pessoa);

        pessoaRepository.save(pessoa);
        musicaRepository.save(musica);

        return DTOFactory.modelToDTO(musica);
    }
}
