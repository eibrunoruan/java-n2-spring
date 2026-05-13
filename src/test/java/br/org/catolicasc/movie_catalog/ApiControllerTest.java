package br.org.catolicasc.movie_catalog;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAllGenres() throws Exception {
        mockMvc.perform(get("/api/genres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Action")));
    }

    @Test
    void testFindAllDirectors() throws Exception {
        mockMvc.perform(get("/api/directors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Christopher Nolan")));
    }

    @Test
    void testSearchMoviesByDirectorAndYear() throws Exception {
        mockMvc.perform(get("/api/movies/search")
                        .param("director", "Christopher Nolan")
                        .param("year", "2010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Inception")));
    }

    @Test
    void testFindMovieSummaries() throws Exception {
        mockMvc.perform(get("/api/movies/summary"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("Inception")))
                .andExpect(jsonPath("$[0].rating", is(9.3)));
    }

    @Test
    void testFindPagedMovies() throws Exception {
        mockMvc.perform(get("/api/movies/paged")
                        .param("page", "0")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(3)));
    }

    @Test
    void testFindCharactersByMovie() throws Exception {
        mockMvc.perform(get("/api/movies/1/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Cobb")));
    }

    @Test
    void testFindCastByMovie() throws Exception {
        mockMvc.perform(get("/api/movies/1/cast"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].artist.name", is("Leonardo DiCaprio")))
                .andExpect(jsonPath("$[0].character.name", is("Cobb")));
    }

    @Test
    void testFindMoviesByArtist() throws Exception {
        mockMvc.perform(get("/api/artists/1/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].movie.title", is("Inception")))
                .andExpect(jsonPath("$[0].character.name", is("Cobb")));
    }
}
