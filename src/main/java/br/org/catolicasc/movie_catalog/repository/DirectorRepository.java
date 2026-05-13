package br.org.catolicasc.movie_catalog.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.org.catolicasc.movie_catalog.model.Director;

@Repository
public interface DirectorRepository extends CrudRepository<Director, Long> {

    List<Director> findByName(String name);

    List<Director> findByNationality(String nationality);
}
