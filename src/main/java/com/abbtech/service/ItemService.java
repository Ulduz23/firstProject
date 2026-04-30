package com.abbtech.service;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseItemDto;

import java.util.List;

public interface ItemService {
    ResponseItemDto add(RequestItemDto request);

    List<ResponseItemDto> bulkUpdate(List<RequestItemDto> request);

    PageResponseDto<ResponseItemDto> getAll(int page, int size);

    ResponseItemDto getById(Long id);

    void deleteById(Long id);

    ResponseItemDto updateById(Long id, RequestItemDto requestItemDto);

    PageResponseDto<ResponseItemDto> getPriceRange(double min, double max, int page, int size);
}
