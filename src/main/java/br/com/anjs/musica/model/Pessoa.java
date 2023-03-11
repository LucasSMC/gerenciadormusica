package br.com.anjs.musica.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Pessoa implements IModel {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String uuid;

    private String nome;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Musica> likes;

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

    public Set<Musica> getLikes() {
        return likes;
    }

    public void setLikes(Set<Musica> likes) {
        this.likes = likes;
    }
}
