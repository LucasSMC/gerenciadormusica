package br.com.anjs.musica.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Musica implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String nome;

    private String artista;

    private String album;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Pessoa> likes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<Pessoa> getLikes() {
        return likes;
    }

    public void setLikes(List<Pessoa> likes) {
        this.likes = likes;
    }
}
