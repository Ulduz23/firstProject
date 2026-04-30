package com.abbtech.service;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;

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
