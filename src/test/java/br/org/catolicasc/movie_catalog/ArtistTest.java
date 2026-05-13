package br.org.catolicasc.movie_catalog;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.org.catolicasc.movie_catalog.model.Artist;
import br.org.catolicasc.movie_catalog.repository.ArtistRepository;

@SpringBootTest
class ArtistTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testSaveArtist() {
        Artist artist = new Artist();
        artist.setName("Anne Hathaway");
        artist.setBirthYear(1982);
        artist.setNationality("American");

        Artist savedArtist = artistRepository.save(artist);

        assertThat(savedArtist.getId()).isNotNull();
        assertThat(savedArtist.getName()).isEqualTo("Anne Hathaway");
    }

    @Test
    void testFindByName() {
        List<Artist> artists = artistRepository.findByName("Leonardo DiCaprio");

        assertThat(artists).isNotEmpty();
        assertThat(artists.getFirst().getBirthYear()).isEqualTo(1974);
    }

    @Test
    void testFindByNationality() {
        List<Artist> artists = artistRepository.findByNationality("American");

        assertThat(artists).isNotEmpty();
        assertThat(artists)
                .extracting(Artist::getName)
                .contains("Leonardo DiCaprio", "Joseph Gordon-Levitt");
    }
}
