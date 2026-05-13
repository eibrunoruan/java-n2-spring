package br.org.catolicasc.movie_catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.movie_catalog.model.Artist;
import br.org.catolicasc.movie_catalog.model.MovieCast;
import br.org.catolicasc.movie_catalog.repository.ArtistRepository;
import br.org.catolicasc.movie_catalog.repository.MovieCastRepository;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MovieCastRepository movieCastRepository;

    @GetMapping
    public ResponseEntity<Iterable<Artist>> findAll() {
        return ResponseEntity.ok(artistRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable Long id) {
        return artistRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieCast>> findMoviesByArtist(@PathVariable Long id) {
        return artistRepository.findById(id)
                .map(artist -> ResponseEntity.ok(movieCastRepository.findByArtist(artist)))
                .orElse(ResponseEntity.notFound().build());
    }
}
