package com.kalalay.backend.graphql;

import com.kalalay.backend.model.Artwork;
import com.kalalay.backend.repository.ArtworkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Optional;

@GraphQlTest(ArtworkController.class)
public class ArtworkControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private ArtworkRepository artworkRepository;

    private Artwork sampleArtwork;

    @BeforeEach
    void setup() {
        sampleArtwork = new Artwork();
        sampleArtwork.setId(1L);
        sampleArtwork.setTitle("Sample");
        sampleArtwork.setShortDescription("Short");
        sampleArtwork.setFullDescription("Full");
        sampleArtwork.setLikes(0);
    }

    @Test
    public void shouldFetchArtworkById() {
        when(artworkRepository.findById(anyLong())).thenReturn(Optional.of(sampleArtwork));

        String query = """
            query {
              artwork(id: 1) {
                id
                title
                shortDescription
              }
            }
        """;

        graphQlTester.document(query)
                .execute()
                .path("artwork.title").entity(String.class).isEqualTo("Sample");
    }
}
