package br.com.anjs.musica.service;

import br.com.anjs.musica.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<T extends IModel, R extends JpaRepository<T,Long>> implements ICrudService<T> {

    @Autowired
    protected R repository;


    @Override
    public T save(T dto) {
        return repository.save(dto);
    }

    @Override
    public T edit(UUID uuid, T dto) {


        return repository.save(dto);
    }


    @Override
    public void delete(T dto) {



    }

    @Override
    public List<T> findAll(){
        return repository.findAll();
    }
}
