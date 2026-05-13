package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {

    List<Artist> findByName(String name);

    List<Artist> findByNationality(String nationality);
}
