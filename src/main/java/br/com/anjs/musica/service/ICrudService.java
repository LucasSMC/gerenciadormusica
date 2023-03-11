package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.IDTOGet;
import br.com.anjs.musica.dto.IDTOPost;

import java.util.List;

public interface ICrudService<M extends IDTOGet,DTO extends IDTOPost> {

    M save(DTO dto);

    M edit(String uuid, DTO dto);

    List<M> findAll();

    void delete(String uuid);

    M findByUUID(String uuid);
}
