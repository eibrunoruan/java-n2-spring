package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.model.MovieCharacter;

@Repository
public interface MovieCharacterRepository extends CrudRepository<MovieCharacter, Long> {

    List<MovieCharacter> findByName(String name);

    List<MovieCharacter> findByMovie(Movie movie);
}
