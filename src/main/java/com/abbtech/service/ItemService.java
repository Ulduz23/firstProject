package com.abbtech.service;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;

import java.util.List;

public interface ItemService {
    ResponseItemDto add(RequestItemDto request);

    void saveAll(List<RequestItemDto> request);

    List<ResponseItemDto> getAll();

    ResponseItemDto getById(Long id);

    void deleteById(Long id);

    ResponseItemDto updateByName(String name, RequestItemDto requestItemDto);

    List<ResponseItemDto> getPriceRange(double min, double max);

    ResponseItemDto partialUpdateByName(String name, String itemDescription);
}
