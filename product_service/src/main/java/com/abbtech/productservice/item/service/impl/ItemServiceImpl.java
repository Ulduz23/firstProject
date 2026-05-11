package com.abbtech.productservice.item.service.impl;

import com.abbtech.productservice.common.annotation.CustomTransactionAnnotation;
import com.abbtech.productservice.item.dto.RequestItemDto;
import com.abbtech.productservice.common.dto.PageResponseDto;
import com.abbtech.productservice.common.dto.RelatedEntityDto;
import com.abbtech.productservice.item.dto.ResponseItemDto;
import com.abbtech.productservice.common.exception.ProductErrorEnum;
import com.abbtech.productservice.common.exception.ProductException;
import com.abbtech.productservice.brand.model.Brand;
import com.abbtech.productservice.category.model.Category;
import com.abbtech.productservice.item.model.Item;
import com.abbtech.productservice.brand.repository.BrandRepository;
import com.abbtech.productservice.category.repository.CategoryRepository;
import com.abbtech.productservice.item.repository.ItemRepository;
import com.abbtech.productservice.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;


    @Override
    @Transactional
    @CustomTransactionAnnotation
    public ResponseItemDto add(RequestItemDto request) {
        Item item = new Item();
        applyRequest(item, request);
        return toResponseDto(itemRepository.save(item));
    }

    @Override
    @Transactional
    @CustomTransactionAnnotation
    public List<ResponseItemDto> bulkUpdate(List<RequestItemDto> requestItems) {
        return requestItems.stream()
                .map(item -> updateById(item.id(), item))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseItemDto> getAll(int page, int size) {
        return PageResponseDto.from(itemRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()))
                .map(this::toResponseDto));
    }

    @Override
    @Transactional(readOnly = true)
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public ResponseItemDto getById(Long id) {
        return toResponseDto(findItemByIdOrThrow(id));
    }

    @Override
    @Transactional
    @CustomTransactionAnnotation
    public void deleteById(Long id) {
        findItemByIdOrThrow(id);
        itemRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CustomTransactionAnnotation
    public ResponseItemDto updateById(Long id, RequestItemDto requestItemDto) {
        Item item = findItemByIdOrThrow(id);
        applyRequest(item, requestItemDto);
        return toResponseDto(itemRepository.save(item));
    }

    @Override
    @Transactional(readOnly = true)
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseItemDto> getPriceRange(double min, double max, int page, int size) {
        return PageResponseDto.from(itemRepository.findByPriceBetween(
                        BigDecimal.valueOf(min),
                        BigDecimal.valueOf(max),
                        PageRequest.of(page, size, Sort.by("id").ascending()))
                .map(this::toResponseDto));
    }

    private Item findItemByIdOrThrow(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.ITEM_NOT_FOUND));
    }

    private void applyRequest(Item item, RequestItemDto request) {
        item.setName(request.name());
        item.setPrice(request.price());
        item.setImage(request.image());
        item.setDescription(request.description());
        item.setIsActive(Boolean.TRUE);
        item.setIsDeleted(Boolean.FALSE);

        if (request.brandId() != null) {
            item.setBrand(brandRepository.findById(request.brandId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND)));
        } else {
            item.setBrand(null);
        }

        if (request.categoryId() != null) {
            item.setCategory(categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new ProductException(ProductErrorEnum.CATEGORY_NOT_FOUND)));
        } else {
            item.setCategory(null);
        }
    }

    private ResponseItemDto toResponseDto(Item item) {
        return new ResponseItemDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription(),
                toRelatedEntityDto(item.getBrand()),
                toRelatedEntityDto(item.getCategory())
        );
    }

    private RelatedEntityDto toRelatedEntityDto(Brand brand) {
        if (brand == null) {
            return null;
        }

        return new RelatedEntityDto(brand.getId(), brand.getName());
    }

    private RelatedEntityDto toRelatedEntityDto(Category category) {
        if (category == null) {
            return null;
        }

        return new RelatedEntityDto(category.getId(), category.getName());
    }

}
