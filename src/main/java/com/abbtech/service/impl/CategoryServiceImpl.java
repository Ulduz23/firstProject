package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseCategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCategoryDto getById(Long id) {
        return toResponseDto(findCategoryByIdOrThrow(id));
    }

    @Override
    @Transactional
    public ResponseCategoryDto add(RequestCategoryDto request) {
        Category category = new Category();
        applyRequest(category, request);
        return toResponseDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public ResponseCategoryDto updateById(Long id, RequestCategoryDto request) {
        Category category = findCategoryByIdOrThrow(id);
        applyRequest(category, request);
        return toResponseDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public List<ResponseCategoryDto> bulkUpdate(List<RequestCategoryDto> categories) {
        return categories.stream()
                .map(category -> updateById(category.getId(), category))
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Category category = findCategoryByIdOrThrow(id);
        itemRepository.deleteByCategory_Id(id);
        categoryRepository.delete(category);
    }

    private Category findCategoryByIdOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.CATEGORY_NOT_FOUND));
    }

    private void applyRequest(Category category, RequestCategoryDto request) {
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImage(request.getImage());
        category.setParentId(request.getParentId());
        category.setCategoryOrder(request.getCategoryOrder() == null ? 1 : request.getCategoryOrder());
        category.setIsActive(request.getIsActive() == null ? Boolean.TRUE : request.getIsActive());
        category.setIsDeleted(request.getIsDeleted() == null ? Boolean.FALSE : request.getIsDeleted());
    }

    private ResponseCategoryDto toResponseDto(Category category) {
        return new ResponseCategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getImage(),
                category.getParentId(),
                category.getCategoryOrder(),
                category.getIsActive(),
                category.getIsDeleted(),
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }
}
