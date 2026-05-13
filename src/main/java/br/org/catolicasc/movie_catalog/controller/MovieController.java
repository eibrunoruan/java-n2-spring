package br.org.catolicasc.movie_catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.movie_catalog.model.Movie;
import br.org.catolicasc.movie_catalog.model.MovieCast;
import br.org.catolicasc.movie_catalog.model.MovieCharacter;
import br.org.catolicasc.movie_catalog.projection.MovieSummary;
import br.org.catolicasc.movie_catalog.repository.GenreRepository;
import br.org.catolicasc.movie_catalog.repository.MovieCastRepository;
import br.org.catolicasc.movie_catalog.repository.MovieCharacterRepository;
import br.org.catolicasc.movie_catalog.repository.MovieRepository;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    @Autowired
    private MovieCastRepository movieCastRepository;

    @GetMapping
    public ResponseEntity<Iterable<Movie>> findAll() {
        return ResponseEntity.ok(movieRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return movieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Movie>> findByGenre(@PathVariable Long genreId) {
        return genreRepository.findById(genreId)
                .map(genre -> ResponseEntity.ok(movieRepository.findByGenre(genre)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchByDirectorAndYear(
            @RequestParam String director,
            @RequestParam Integer year) {
        return ResponseEntity.ok(movieRepository.findByDirectorNameAndYear(director, year));
    }

    @GetMapping("/rating-above")
    public ResponseEntity<List<Movie>> findByRatingAbove(@RequestParam Double rating) {
        return ResponseEntity.ok(movieRepository.findByRatingAbove(rating));
    }

    @GetMapping("/summary")
    public ResponseEntity<List<MovieSummary>> getSummaries() {
        return ResponseEntity.ok(movieRepository.findMovieSummaries());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Movie>> getPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("rating").descending());
        return ResponseEntity.ok(movieRepository.findAll(pageable));
    }

    @GetMapping("/{id}/characters")
    public ResponseEntity<List<MovieCharacter>> findCharactersByMovie(@PathVariable Long id) {
        return movieRepository.findById(id)
                .map(movie -> ResponseEntity.ok(movieCharacterRepository.findByMovie(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/cast")
    public ResponseEntity<List<MovieCast>> findCastByMovie(@PathVariable Long id) {
        return movieRepository.findById(id)
                .map(movie -> ResponseEntity.ok(movieCastRepository.findByMovie(movie)))
                .orElse(ResponseEntity.notFound().build());
    }
}
