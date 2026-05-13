package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Genre;
import br.org.catolicasc.movie_catalog.repository.GenreRepository;

@SpringBootTest
class GenreTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void testSaveGenre() {
        Genre genre = new Genre();
        genre.setName("Thriller");

        Genre savedGenre = genreRepository.save(genre);

        assertThat(savedGenre.getId()).isNotNull();
        assertThat(savedGenre.getName()).isEqualTo("Thriller");
    }

    @Test
    void testFindByName() {
        List<Genre> genres = genreRepository.findByName("Action");

        assertThat(genres).isNotEmpty();
        assertThat(genres.getFirst().getName()).isEqualTo("Action");
    }

    @Test
    void testFindAllGenres() {
        Iterable<Genre> genres = genreRepository.findAll();

        assertThat(genres).isNotEmpty();
    }
}
