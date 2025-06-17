package com.kalalay.backend.graphql;

import com.kalalay.backend.model.Artwork;
import com.kalalay.backend.repository.ArtworkRepository;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;
import java.util.Optional;

@Component
public class ArtworkController {

    private final ArtworkRepository artworkRepository;

    public ArtworkController(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @QueryMapping
    public List<Artwork> allArtworks() {
        System.out.println("Fetching all artworks...");
        List<Artwork> artworks = artworkRepository.findAll();
        System.out.println("Artworks fetched: " + artworks);
        return artworks;
    }

    @QueryMapping
    public Optional<Artwork> artwork(@Argument Long id) {
        return artworkRepository.findById(id);
    }

    @MutationMapping
    public Artwork likeArtwork(@Argument Long id) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));
        artwork.setLikes(artwork.getLikes() + 1);
        return artworkRepository.save(artwork);
    }
}
