package br.com.anjs.musica.repository;

import br.com.anjs.musica.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {

    @Query("SELECT p FROM Playlist p WHERE  p.uuid= :uuid")
    Playlist findByUUID(@Param("uuid") String uuid);
}
