package br.com.anjs.musica.dto.operacao;

import br.com.anjs.musica.dto.IDTOPost;

import javax.validation.constraints.NotBlank;

public class AdicionarMusicaPlaylistDTO implements IDTOPost {
    @NotBlank(message = "UUID da playlist não pode estar em branco")
    public String uuidPlaylist;

    @NotBlank(message = "UUID da Música não pode estar em branco")
    public String uuidMusica;

}
