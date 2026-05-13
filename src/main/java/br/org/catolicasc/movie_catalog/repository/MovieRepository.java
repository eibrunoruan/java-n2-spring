package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Genre;
import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.projection.MovieSummary;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long>, PagingAndSortingRepository<Movie, Long> {

    List<Movie> findByTitle(String title);

    List<Movie> findByGenre(Genre genre);

    List<Movie> findByRatingGreaterThan(Double rating);

    @Query("SELECT m FROM Movie m WHERE m.director.name = :director AND m.releaseYear = :year")
    List<Movie> findByDirectorNameAndYear(@Param("director") String director,
                                           @Param("year") Integer year);

    @Query(value = "SELECT * FROM movie WHERE rating > :rating", nativeQuery = true)
    List<Movie> findByRatingAbove(@Param("rating") Double rating);

    @Query(value = "SELECT m.title AS title, m.rating AS rating FROM movie m", nativeQuery = true)
    List<MovieSummary> findMovieSummaries();

    Page<Movie> findAll(Pageable pageable);
}
