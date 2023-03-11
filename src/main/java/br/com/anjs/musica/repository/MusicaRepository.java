package br.com.anjs.musica.repository;

import br.com.anjs.musica.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<Musica,Long> {

    @Query("SELECT m from Musica m where m.uuid= :uuid")
    Musica findByUUID(@Param("uuid") String uuid);

}
