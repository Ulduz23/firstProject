package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Category;
import com.abbtech.repository.CategoryRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public PageResponseDto<ResponseCategoryDto> getAll(int page, int size) {
        return PageResponseDto.from(categoryRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()))
                .map(this::toResponseDto));
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
                .map(category -> updateById(category.id(), category))
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
        category.setName(request.name());
        category.setDescription(request.description());
        category.setImage(request.image());
        category.setParentId(request.parentId());
        category.setCategoryOrder(request.categoryOrder() == null ? 1 : request.categoryOrder());
        category.setIsActive(request.isActive() == null ? Boolean.TRUE : request.isActive());
        category.setIsDeleted(request.isDeleted() == null ? Boolean.FALSE : request.isDeleted());
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
