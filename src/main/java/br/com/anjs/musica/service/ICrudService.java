package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.IDTOGet;
import br.com.anjs.musica.dto.IDTOPost;
import br.com.anjs.musica.exceptions.EntidadeNaoEncontrada;

import java.util.List;

public interface ICrudService<M extends IDTOGet,DTO extends IDTOPost> {

    M save(DTO dto);

    M edit(String uuid, DTO dto) throws EntidadeNaoEncontrada;

    List<M> findAll();

    void delete(String uuid) throws EntidadeNaoEncontrada;

    M findByUUID(String uuid) throws EntidadeNaoEncontrada;
}
