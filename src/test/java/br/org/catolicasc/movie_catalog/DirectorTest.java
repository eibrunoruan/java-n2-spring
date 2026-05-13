package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Director;
import br.org.catolicasc.movie_catalog.repository.DirectorRepository;

@SpringBootTest
class DirectorTest {

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    void testSaveDirector() {
        Director director = new Director();
        director.setName("Greta Gerwig");
        director.setBirthYear(1983);
        director.setNationality("American");

        Director savedDirector = directorRepository.save(director);

        assertThat(savedDirector.getId()).isNotNull();
        assertThat(savedDirector.getName()).isEqualTo("Greta Gerwig");
    }

    @Test
    void testFindByName() {
        List<Director> directors = directorRepository.findByName("Christopher Nolan");

        assertThat(directors).isNotEmpty();
        assertThat(directors.getFirst().getBirthYear()).isEqualTo(1970);
    }

    @Test
    void testFindByNationality() {
        List<Director> directors = directorRepository.findByNationality("British");

        assertThat(directors).isNotEmpty();
        assertThat(directors)
                .extracting(Director::getName)
                .contains("Ridley Scott");
    }
}
