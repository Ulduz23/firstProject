package com.abbtech.repository;

import com.abbtech.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    Optional<Brand> findByNameAndImageAndDescriptionLikeIgnoreCase(String name, String imageUrl, String description);

}

