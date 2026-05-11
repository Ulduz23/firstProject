package com.abbtech.productservice.brand.repository;

import com.abbtech.productservice.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String name);

    Optional<Brand> findByNameAndImageAndDescriptionLikeIgnoreCase(String name, String imageUrl, String description);

}

