package br.com.anjs.musica.repository;

import br.com.anjs.musica.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long> {

    @Query("SELECT g from Genero g where  g.uuid= :uuid")
    Genero findByUUID(@Param("uuid") String uuid);

}
