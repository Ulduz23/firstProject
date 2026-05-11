package com.abbtech.productservice.brand.service;

import com.abbtech.productservice.brand.dto.RequestBrandDto;
import com.abbtech.productservice.common.dto.PageResponseDto;
import com.abbtech.productservice.brand.dto.ResponseBrandDto;
import com.abbtech.productservice.item.dto.ResponseItemDto;

import java.util.List;

public interface BrandService {
    PageResponseDto<ResponseBrandDto> getAll(int page, int size);

    ResponseBrandDto getById(Long id);

    ResponseBrandDto add(RequestBrandDto request);

    ResponseBrandDto updateById(Long id, RequestBrandDto request);

    List<ResponseBrandDto> bulkUpdate(List<RequestBrandDto> requests);

    void deleteById(Long id);

    PageResponseDto<ResponseItemDto> getItemsByBrand(Long brandId, int page, int size);

}
