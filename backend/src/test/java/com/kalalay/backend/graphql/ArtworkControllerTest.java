package com.kalalay.backend.graphql;

import com.kalalay.backend.dto.ArtworkDTO;
import com.kalalay.backend.model.Artwork;
import com.kalalay.backend.repository.ArtworkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ArtworkControllerTest {

    @Mock
    private ArtworkRepository artworkRepository;

    @InjectMocks
    private ArtworkController artworkController;

    private Artwork sampleArtwork;

    @BeforeEach
    void setup() {
        sampleArtwork = new Artwork();
        sampleArtwork.setId(1L);
        sampleArtwork.setTitle("Mock Title");
        sampleArtwork.setShortDescription("Short desc");
        sampleArtwork.setFullDescription("Full desc");
        sampleArtwork.setLikes(5);
    }

    @Test
    void testGetAllArtworks() {
        when(artworkRepository.findAll()).thenReturn(List.of(sampleArtwork));

        List<ArtworkDTO> result = artworkController.allArtworks();

        assertEquals(1, result.size());
        assertEquals("Mock Title", result.get(0).getTitle());
    }

    @Test
    void testGetArtworkById() {
        when(artworkRepository.findById(1L)).thenReturn(Optional.of(sampleArtwork));

        ArtworkDTO result = artworkController.artwork(1L);  // Return type changed

        assertNotNull(result);
        assertEquals("Mock Title", result.getTitle());
    }


    @Test
    void testLikeArtwork() {
        when(artworkRepository.findById(1L)).thenReturn(Optional.of(sampleArtwork));
        when(artworkRepository.save(any(Artwork.class))).thenAnswer(i -> i.getArgument(0));

        ArtworkDTO updated = artworkController.likeArtwork(1L);

        assertEquals(6, updated.getLikes());
    }

    @Test
    void testDeleteArtwork() {
        when(artworkRepository.existsById(1L)).thenReturn(true);
        doNothing().when(artworkRepository).deleteById(1L);

        boolean deleted = artworkController.deleteArtwork(1L);

        assertTrue(deleted);
    }
}
