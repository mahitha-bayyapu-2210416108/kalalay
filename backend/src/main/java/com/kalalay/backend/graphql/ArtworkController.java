package com.kalalay.backend.graphql;

import com.kalalay.backend.dto.ArtworkDTO;
import com.kalalay.backend.mapper.ArtworkMapper;
import com.kalalay.backend.model.Artwork;
import com.kalalay.backend.repository.ArtworkRepository;
import org.springframework.stereotype.Component;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Component
public class ArtworkController {

    private final ArtworkRepository artworkRepository;

    public ArtworkController(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @QueryMapping
    public List<ArtworkDTO> allArtworks() {
        return artworkRepository.findAll()
                .stream()
                .map(ArtworkMapper::toDTO)
                .collect(Collectors.toList());
    }

    @QueryMapping
    public ArtworkDTO artwork(@Argument Long id) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));
        return ArtworkMapper.toDTO(artwork);
    }

    @MutationMapping
    public ArtworkDTO likeArtwork(@Argument Long id) {
        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));
        artwork.setLikes(artwork.getLikes() + 1);
        return ArtworkMapper.toDTO(artworkRepository.save(artwork));
    }

    @MutationMapping
    public ArtworkDTO addArtwork(
            @Argument String title,
            @Argument String imageUrl,
            @Argument String shortDescription,
            @Argument String fullDescription) {

        Artwork artwork = new Artwork();
        artwork.setTitle(title);
        artwork.setImageUrl(imageUrl);
        artwork.setShortDescription(shortDescription);
        artwork.setFullDescription(fullDescription);

        return ArtworkMapper.toDTO(artworkRepository.save(artwork));
    }

    @MutationMapping
    public Boolean deleteArtwork(@Argument Long id) {
        if (artworkRepository.existsById(id)) {
            artworkRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @MutationMapping
    public ArtworkDTO updateArtwork(
            @Argument Long id,
            @Argument String title,
            @Argument String imageUrl,
            @Argument String shortDescription,
            @Argument String fullDescription) {

        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artwork not found"));

        if (title != null) artwork.setTitle(title);
        if (imageUrl != null) artwork.setImageUrl(imageUrl);
        if (shortDescription != null) artwork.setShortDescription(shortDescription);
        if (fullDescription != null) artwork.setFullDescription(fullDescription);

        return ArtworkMapper.toDTO(artworkRepository.save(artwork));
    }

    @QueryMapping
    public List<ArtworkDTO> searchArtworks(@Argument String query) {
        return artworkRepository
                .findByTitleContainingIgnoreCaseOrShortDescriptionContainingIgnoreCase(query, query)
                .stream()
                .map(ArtworkMapper::toDTO)
                .collect(Collectors.toList());
    }


}
