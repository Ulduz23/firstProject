package com.abbtech.service.impl;

import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
    }

    @Override
    @Transactional
    public Category add(Category category) {
        if (category.getCategoryOrder() == null) {
            category.setCategoryOrder(1);
        }
        if (category.getIsActive() == null) {
            category.setIsActive(true);
        }
        if (category.getIsDeleted() == null) {
            category.setIsDeleted(false);
        }
        categoryRepository.save(category);
        return category;
    }

    @Override
    @Transactional
    public Category updateById(Long id, Category category) {
//        getById(id);
//        categoryRepository.updateById(id, category);
//        category.setId(id == null ? null : id.intValue());
//        return category;

        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

