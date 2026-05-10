package com.abbtech.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abbtech.annotation.CustomTransactionAnnotation;
import com.abbtech.annotation.full.FullValueAnnotation;
import com.abbtech.annotation.marked.MarkedAnnotation;
import com.abbtech.annotation.repeatable.RepeatableAnnotation;
import com.abbtech.annotation.singlevalue.SingleValueAnnotation;
import com.abbtech.annotation.typed.TypedAnnotation;
import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.RelatedEntityDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Brand;
import com.abbtech.model.Category;
import com.abbtech.repository.BrandRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.BrandService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@TypedAnnotation(returnType = "PageResponseDto<ResponseBrandDto>")
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ItemRepository itemRepository;

    @Override
    @Transactional(readOnly = true)
    @TypedAnnotation(returnType = "PageResponseDto<ResponseBrandDto>")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseBrandDto> getAll(int page, int size) {
        var brands = brandRepository.findAll(PageRequest.of(page, size, Sort.by("id").ascending()));
        log.debug("Returning brand page. page={}, size={}", page, size);
        return PageResponseDto.from(brands.map(this::toResponseDto));
    }

    @Override
    @Transactional(readOnly = true)
    @TypedAnnotation(returnType = "ResponseBrandDto")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public ResponseBrandDto getById(Long id) {
        return toResponseDto(findBrandByIdOrThrow(id));
    }

    @Override
    @Transactional
    @MarkedAnnotation
    @SingleValueAnnotation("1")
    @CustomTransactionAnnotation
    public ResponseBrandDto add(RequestBrandDto request) {
        Brand brand = toBrand(request);
        return toResponseDto(brandRepository.save(brand));
    }

    @Override
    @Transactional
    @SingleValueAnnotation("2")
    @CustomTransactionAnnotation
    public ResponseBrandDto updateById(Long id, RequestBrandDto request) {
        Brand existingBrand = findBrandByIdOrThrow(id);
        applyRequest(existingBrand, request);
        return toResponseDto(brandRepository.save(existingBrand));
    }

    @Override
    @Transactional
    @SingleValueAnnotation("bulk-update")
    @CustomTransactionAnnotation
    public List<ResponseBrandDto> bulkUpdate(List<RequestBrandDto> requests) {
        return requests.stream()
                .map(request -> updateById(request.id(), request))
                .toList();
    }

    @Override
    @Transactional
    @SingleValueAnnotation("3")
    @FullValueAnnotation(order = 1, name = "deleteById", description = "deleteById service method")
    @TypedAnnotation(returnType = "void")
    @CustomTransactionAnnotation
    public void deleteById(Long id) {
        Brand brand = findBrandByIdOrThrow(id);
        itemRepository.deleteByBrand_Id(id);
        brandRepository.delete(brand);
    }

    @Override
    @Transactional(readOnly = true)
    @TypedAnnotation(returnType = "PageResponseDto<ResponseItemDto>")
    @RepeatableAnnotation("brandId parameter checked")
    @RepeatableAnnotation("returns paged brand items")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseItemDto> getItemsByBrand(Long brandId, int page, int size) {
        findBrandByIdOrThrow(brandId);
        var items = itemRepository.findByBrand_Id(brandId, PageRequest.of(page, size, Sort.by("id").ascending()));
        return PageResponseDto.from(items.map(item -> new ResponseItemDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getImage(),
                item.getDescription(),
                toRelatedEntityDto(item.getBrand()),
                toRelatedEntityDto(item.getCategory()))));
    }

    private Brand findBrandByIdOrThrow(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND));
    }

    private Brand toBrand(RequestBrandDto request) {
        Brand brand = new Brand();
        applyRequest(brand, request);
        return brand;
    }

    private void applyRequest(Brand brand, RequestBrandDto request) {
        brand.setName(request.name());
        brand.setDescription(request.description());
        brand.setImage(request.image());
        brand.setIsActive(request.isActive() == null ? Boolean.TRUE : request.isActive());
        brand.setIsDeleted(request.isDeleted() == null ? Boolean.FALSE : request.isDeleted());
    }

    private ResponseBrandDto toResponseDto(Brand brand) {
        return new ResponseBrandDto(
                brand.getId(),
                brand.getName(),
                brand.getDescription(),
                brand.getImage(),
                brand.getIsActive(),
                brand.getIsDeleted(),
                brand.getCreatedAt(),
                brand.getUpdatedAt()
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
