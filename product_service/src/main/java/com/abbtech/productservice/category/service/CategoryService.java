package com.abbtech.productservice.category.service;

import com.abbtech.productservice.category.dto.RequestCategoryDto;
import com.abbtech.productservice.common.dto.PageResponseDto;
import com.abbtech.productservice.category.dto.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {
    PageResponseDto<ResponseCategoryDto> getAll(int page, int size);

    ResponseCategoryDto getById(Long id);

    ResponseCategoryDto add(RequestCategoryDto category);

    ResponseCategoryDto updateById(Long id, RequestCategoryDto category);

    List<ResponseCategoryDto> bulkUpdate(List<RequestCategoryDto> categories);

    void deleteById(Long id);
}
