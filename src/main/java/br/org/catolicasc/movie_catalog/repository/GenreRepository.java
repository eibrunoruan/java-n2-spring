package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Genre;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findByName(String name);
}
