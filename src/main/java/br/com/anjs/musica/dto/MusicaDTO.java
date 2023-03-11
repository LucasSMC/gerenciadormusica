package br.com.anjs.musica.dto;

import java.util.UUID;

public class MusicaDTO implements IDTO{

    public UUID uuid;

    public String nome;

    public String artista;

    public String album;

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}

