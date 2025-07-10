package com.kalalay.backend.repository;

import com.kalalay.backend.model.Artwork;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ArtworkRepositoryTest {

    @Autowired
    private ArtworkRepository artworkRepository;

    @Test
    public void shouldSaveAndFindArtwork() {
        Artwork artwork = new Artwork();
        artwork.setTitle("Test Title");
        artwork.setShortDescription("Test short desc");
        artwork.setFullDescription("Test full desc");
        artwork.setImageUrl("http://example.com/image.png");

        artworkRepository.save(artwork);

        List<Artwork> results = artworkRepository.findByTitleContainingIgnoreCaseOrShortDescriptionContainingIgnoreCase("Test", "Test");

        Assertions.assertThat(results).isNotEmpty();
        Assertions.assertThat(results.get(0).getTitle()).isEqualTo("Test Title");
    }
}
