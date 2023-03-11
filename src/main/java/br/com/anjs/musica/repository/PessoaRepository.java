package br.com.anjs.musica.repository;

import br.com.anjs.musica.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    @Query("SELECT p FROM Pessoa p WHERE p.uuid= :uuid")
    Pessoa findByUUID(@Param("uuid") String uuid);

}
