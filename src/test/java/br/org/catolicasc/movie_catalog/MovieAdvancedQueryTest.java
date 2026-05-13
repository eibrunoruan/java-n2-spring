package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.projection.MovieSummary;
import br.org.catolicasc.movie_catalog.repository.MovieRepository;

@SpringBootTest
class MovieAdvancedQueryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void testFindByDirectorNameAndYear() {
        List<Movie> movies = movieRepository.findByDirectorNameAndYear("Christopher Nolan", 2010);

        assertThat(movies).isNotEmpty();
        assertThat(movies)
                .extracting(Movie::getTitle)
                .contains("Inception");
    }

    @Test
    void testFindByRatingAboveNativeQuery() {
        List<Movie> movies = movieRepository.findByRatingAbove(9.0);

        assertThat(movies).isNotEmpty();
        assertThat(movies)
                .extracting(Movie::getTitle)
                .contains("Inception", "Interstellar");
    }

    @Test
    void testFindMovieSummaries() {
        List<MovieSummary> summaries = movieRepository.findMovieSummaries();

        assertThat(summaries).isNotEmpty();
        assertThat(summaries)
                .extracting(MovieSummary::getTitle)
                .contains("Inception", "Interstellar");
    }

    @Test
    void testPagedMovies() {
        PageRequest pageable = PageRequest.of(0, 3, Sort.by("rating").descending());

        Page<Movie> page = movieRepository.findAll(pageable);

        assertThat(page.getContent()).isNotEmpty();
        assertThat(page.getContent()).hasSize(3);
        assertThat(page.getTotalElements()).isGreaterThanOrEqualTo(5);
        assertThat(page.getContent().getFirst().getRating()).isGreaterThanOrEqualTo(page.getContent().getLast().getRating());
    }
}
