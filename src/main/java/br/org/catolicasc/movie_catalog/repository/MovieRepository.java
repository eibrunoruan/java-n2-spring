package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Genre;
import br.org.catolicasc.movie_catalog.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(Genre genre);

    List<Movie> findByRatingGreaterThan(Double rating);
}
