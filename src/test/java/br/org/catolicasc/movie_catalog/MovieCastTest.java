package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Artist;
import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.model.MovieCast;
import br.org.catolicasc.movie_catalog.model.MovieCharacter;
import br.org.catolicasc.movie_catalog.repository.ArtistRepository;
import br.org.catolicasc.movie_catalog.repository.MovieCastRepository;
import br.org.catolicasc.movie_catalog.repository.MovieCharacterRepository;
import br.org.catolicasc.movie_catalog.repository.MovieRepository;

@SpringBootTest
class MovieCastTest {

    @Autowired
    private MovieCastRepository movieCastRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    @Test
    void testSaveMovieCast() {
        Movie movie = movieRepository.findByTitle("Inception").getFirst();
        Artist artist = artistRepository.findByName("Leonardo DiCaprio").getFirst();
        MovieCharacter character = movieCharacterRepository.findByName("Cobb").getFirst();

        MovieCast movieCast = new MovieCast();
        movieCast.setMovie(movie);
        movieCast.setArtist(artist);
        movieCast.setCharacter(character);
        movieCast.setCastRole("LEAD");

        MovieCast savedMovieCast = movieCastRepository.save(movieCast);

        assertThat(savedMovieCast.getId()).isNotNull();
        assertThat(savedMovieCast.getMovie().getTitle()).isEqualTo("Inception");
        assertThat(savedMovieCast.getArtist().getName()).isEqualTo("Leonardo DiCaprio");
        assertThat(savedMovieCast.getCharacter().getName()).isEqualTo("Cobb");
    }

    @Test
    void testFindByMovie() {
        Movie movie = movieRepository.findByTitle("Inception").getFirst();

        List<MovieCast> cast = movieCastRepository.findByMovie(movie);

        assertThat(cast).isNotEmpty();
        assertThat(cast)
                .extracting(movieCast -> movieCast.getArtist().getName())
                .contains("Leonardo DiCaprio", "Joseph Gordon-Levitt");
    }

    @Test
    void testFindByArtist() {
        Artist artist = artistRepository.findByName("Leonardo DiCaprio").getFirst();

        List<MovieCast> participations = movieCastRepository.findByArtist(artist);

        assertThat(participations).isNotEmpty();
        assertThat(participations)
                .extracting(movieCast -> movieCast.getMovie().getTitle())
                .contains("Inception");
    }

    @Test
    void testFindByCharacter() {
        MovieCharacter character = movieCharacterRepository.findByName("Cobb").getFirst();

        List<MovieCast> cast = movieCastRepository.findByCharacter(character);

        assertThat(cast).isNotEmpty();
        assertThat(cast)
                .extracting(movieCast -> movieCast.getArtist().getName())
                .contains("Leonardo DiCaprio");
    }

    @Test
    void testFindByCastRole() {
        List<MovieCast> leads = movieCastRepository.findByCastRole("LEAD");

        assertThat(leads).isNotEmpty();
        assertThat(leads)
                .extracting(movieCast -> movieCast.getCharacter().getName())
                .contains("Cobb", "Cooper", "Maximus");
    }
}
