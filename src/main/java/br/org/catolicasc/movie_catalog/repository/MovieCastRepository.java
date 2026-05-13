package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Artist;
import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.model.MovieCast;
import br.org.catolicasc.movie_catalog.model.MovieCharacter;

@Repository
public interface MovieCastRepository extends CrudRepository<MovieCast, Long> {

    List<MovieCast> findByMovie(Movie movie);

    List<MovieCast> findByArtist(Artist artist);

    List<MovieCast> findByCharacter(MovieCharacter character);

    List<MovieCast> findByCastRole(String castRole);
}
