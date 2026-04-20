package com.abbtech.service;

import com.abbtech.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category add(Category category);

    Category updateById(Long id, Category category);

    void deleteById(Long id);
}

