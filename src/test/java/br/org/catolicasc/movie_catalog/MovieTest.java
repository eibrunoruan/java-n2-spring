package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Genre;
import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.repository.GenreRepository;
import br.org.catolicasc.movie_catalog.repository.MovieRepository;

@SpringBootTest
class MovieTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testSaveMovie() {
        Genre genre = genreRepository.findByName("Action").getFirst();

        Movie movie = new Movie();
        movie.setTitle("Mad Max: Fury Road");
        movie.setDirector("George Miller");
        movie.setReleaseYear(2015);
        movie.setRating(8.1);
        movie.setGenre(genre);

        Movie savedMovie = movieRepository.save(movie);

        assertThat(savedMovie.getId()).isNotNull();
        assertThat(savedMovie.getTitle()).isEqualTo("Mad Max: Fury Road");
        assertThat(savedMovie.getGenre().getName()).isEqualTo("Action");
    }

    @Test
    void testFindByTitle() {
        List<Movie> movies = movieRepository.findByTitle("Inception");

        assertThat(movies).isNotEmpty();
        assertThat(movies.getFirst().getDirector()).isEqualTo("Christopher Nolan");
    }

    @Test
    void testFindByGenre() {
        Genre genre = genreRepository.findByName("Science Fiction").getFirst();

        List<Movie> movies = movieRepository.findByGenre(genre);

        assertThat(movies).isNotEmpty();
        assertThat(movies)
                .extracting(Movie::getTitle)
                .contains("Inception", "Interstellar");
    }

    @Test
    void testFindByRatingGreaterThan() {
        List<Movie> movies = movieRepository.findByRatingGreaterThan(9.0);

        assertThat(movies).isNotEmpty();
        assertThat(movies)
                .extracting(Movie::getTitle)
                .contains("Inception", "Interstellar");
    }
}
