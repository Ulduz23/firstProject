package com.abbtech.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.exception.ProductErrorEnum;
import com.abbtech.exception.ProductException;
import com.abbtech.model.Brand;
import com.abbtech.repository.BrandRepository;
import com.abbtech.service.BrandService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ResponseBrandDto> getAll() {
        var brands = brandRepository.findAll();
        return brands.stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseBrandDto getById(Long id) {

        var optionalBrand = brandRepository.findById(id);

        return toResponseDto(optionalBrand.orElseThrow());
    }

    @Override
    @Transactional
    public ResponseBrandDto add(RequestBrandDto request) {
        Brand brand = toBrand(request);
        return toResponseDto(brandRepository.save(brand));
    }

    @Override
    @Transactional
    public ResponseBrandDto updateById(Long id, RequestBrandDto request) {
        Brand existingBrand = findBrandByIdOrThrow(id);
        applyRequest(existingBrand, request);
        return toResponseDto(brandRepository.save(existingBrand));
    }

    @Override
    @Transactional
    public List<ResponseBrandDto> bulkUpdate(List<RequestBrandDto> requests) {
        return requests.stream()
                .map(request -> updateById(request.getId(), request))
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseItemDto> getItemsByBrand(Long brandId) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new ProductException(ProductErrorEnum.BRAND_NOT_FOUND));
        return new ArrayList<>(brand.getItems().stream()
                .map(item -> new ResponseItemDto(
                        item.getId(),
                        item.getName(),
                        item.getPrice(),
                        item.getImage(),
                        item.getDescription(),
                        item.getBrand() == null ? null : item.getBrand().getId(),
                        item.getCategory() == null ? null : item.getCategory().getId()))
                .toList());
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
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setImage(request.getImage());
        brand.setIsActive(request.getIsActive() == null ? Boolean.TRUE : request.getIsActive());
        brand.setIsDeleted(request.getIsDeleted() == null ? Boolean.FALSE : request.getIsDeleted());
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
}
