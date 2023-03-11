package br.com.anjs.musica.rest;

import br.com.anjs.musica.dto.GeneroDTO;
import br.com.anjs.musica.dto.IDTO;
import br.com.anjs.musica.factory.IDTOFactory;
import br.com.anjs.musica.factory.IModelFactory;
import br.com.anjs.musica.factory.ModelFactory;
import br.com.anjs.musica.model.IModel;
import br.com.anjs.musica.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public abstract class AbstractRest<T extends ICrudService, DTO extends IDTO,F extends IDTOFactory, MF extends IModelFactory> {

    @Autowired
    protected ICrudService service;

    @Autowired
    protected F factoryDTO;

    @Autowired
    protected MF factoryModel;

    @Autowired
    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll().stream().map((IModel) M-> factoryDTO.modelToDTO(M)));
    }

    @PostMapping
    public ResponseEntity criar(DTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(factoryDTO.modelToDTO(service.save(factoryModel.DTOToModel(dto))));
    }

    @PutMapping
    public ResponseEntity editar(GeneroDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(factoryDTO.modelToDTO(service.edit(factoryModel.DTOToModel(dto))));
    }

    @DeleteMapping
    public ResponseEntity deletar(GeneroDTO dto) {

        service.delete(service.findByUUID(dto.uuid));
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
