package br.com.anjs.musica.service;

import br.com.anjs.musica.model.Genero;
import br.com.anjs.musica.model.Pessoa;
import br.com.anjs.musica.repository.PessoaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PessoaService extends AbstractService<Pessoa, PessoaRepository> {
    @Override
    public Pessoa findByUUID(UUID uuid) {
        return super.repository.findByUUID(uuid);
    }
}
