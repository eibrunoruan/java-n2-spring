package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.model.MovieCharacter;
import br.org.catolicasc.movie_catalog.repository.MovieCharacterRepository;
import br.org.catolicasc.movie_catalog.repository.MovieRepository;

@SpringBootTest
class MovieCharacterTest {

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testSaveMovieCharacter() {
        Movie movie = movieRepository.findByTitle("Inception").getFirst();

        MovieCharacter character = new MovieCharacter();
        character.setName("Mal");
        character.setDescription("Personagem ligada ao passado de Cobb.");
        character.setMovie(movie);

        MovieCharacter savedCharacter = movieCharacterRepository.save(character);

        assertThat(savedCharacter.getId()).isNotNull();
        assertThat(savedCharacter.getMovie().getTitle()).isEqualTo("Inception");
    }

    @Test
    void testFindByName() {
        List<MovieCharacter> characters = movieCharacterRepository.findByName("Cobb");

        assertThat(characters).isNotEmpty();
        assertThat(characters.getFirst().getMovie().getTitle()).isEqualTo("Inception");
    }

    @Test
    void testFindByMovie() {
        Movie movie = movieRepository.findByTitle("Inception").getFirst();

        List<MovieCharacter> characters = movieCharacterRepository.findByMovie(movie);

        assertThat(characters).isNotEmpty();
        assertThat(characters)
                .extracting(MovieCharacter::getName)
                .contains("Cobb", "Arthur");
    }
}
