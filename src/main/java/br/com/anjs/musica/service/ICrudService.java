package br.com.anjs.musica.service;

import br.com.anjs.musica.dto.IDTO;
import br.com.anjs.musica.model.IModel;

import java.util.List;
import java.util.UUID;

public interface ICrudService<T extends IModel> {

    T save(T dto);

    T edit(T dto);

    List<T> findAll();

    void delete(T dto);

    T findByUUID(UUID uuid);
}
