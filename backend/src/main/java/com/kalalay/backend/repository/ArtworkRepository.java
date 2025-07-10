package com.kalalay.backend.repository;

import com.kalalay.backend.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
    List<Artwork> findByTitleContainingIgnoreCaseOrShortDescriptionContainingIgnoreCase(String title, String shortDescription);

}
