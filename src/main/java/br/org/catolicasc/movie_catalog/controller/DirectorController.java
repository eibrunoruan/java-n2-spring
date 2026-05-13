package br.org.catolicasc.movie_catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.catolicasc.movie_catalog.model.Director;
import br.org.catolicasc.movie_catalog.repository.DirectorRepository;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    @Autowired
    private DirectorRepository directorRepository;

    @GetMapping
    public ResponseEntity<Iterable<Director>> findAll() {
        return ResponseEntity.ok(directorRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Director> findById(@PathVariable Long id) {
        return directorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
