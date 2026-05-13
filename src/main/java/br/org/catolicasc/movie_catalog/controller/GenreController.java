package br.org.catolicasc.movie_catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.movie_catalog.model.Genre;
import br.org.catolicasc.movie_catalog.repository.GenreRepository;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public ResponseEntity<Iterable<Genre>> findAll() {
        return ResponseEntity.ok(genreRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Long id) {
        return genreRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
