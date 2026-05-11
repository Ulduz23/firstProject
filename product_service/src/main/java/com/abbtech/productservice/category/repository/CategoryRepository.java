package com.abbtech.productservice.category.repository;

import com.abbtech.productservice.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}

