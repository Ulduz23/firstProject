package com.abbtech.service;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {
    PageResponseDto<ResponseCategoryDto> getAll(int page, int size);

    ResponseCategoryDto getById(Long id);

    ResponseCategoryDto add(RequestCategoryDto category);

    ResponseCategoryDto updateById(Long id, RequestCategoryDto category);

    List<ResponseCategoryDto> bulkUpdate(List<RequestCategoryDto> categories);

    void deleteById(Long id);
}
