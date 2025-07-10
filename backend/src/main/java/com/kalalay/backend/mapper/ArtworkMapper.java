package com.kalalay.backend.mapper;

import com.kalalay.backend.dto.ArtworkDTO;
import com.kalalay.backend.model.Artwork;

public class ArtworkMapper {

    public static ArtworkDTO toDTO(Artwork artwork) {
        ArtworkDTO dto = new ArtworkDTO();
        dto.setId(artwork.getId());
        dto.setTitle(artwork.getTitle());
        dto.setImageUrl(artwork.getImageUrl());
        dto.setShortDescription(artwork.getShortDescription());
        dto.setFullDescription(artwork.getFullDescription());
        dto.setLikes(artwork.getLikes());
        return dto;
    }

    public static Artwork toEntity(ArtworkDTO dto) {
        Artwork artwork = new Artwork();
        artwork.setId(dto.getId()); // Use only if updating
        artwork.setTitle(dto.getTitle());
        artwork.setImageUrl(dto.getImageUrl());
        artwork.setShortDescription(dto.getShortDescription());
        artwork.setFullDescription(dto.getFullDescription());
        artwork.setLikes(dto.getLikes());
        return artwork;
    }
}
