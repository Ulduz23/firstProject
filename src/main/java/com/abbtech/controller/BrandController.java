package com.abbtech.controller;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.BrandService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public PageResponseDto<ResponseBrandDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return brandService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ResponseBrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBrandDto add(@RequestBody @Valid RequestBrandDto request) {
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseBrandDto updateById(@PathVariable Long id, @RequestBody @Valid RequestBrandDto request) {
        return brandService.updateById(id, request);
    }

    @PutMapping("/bulk")
    public List<ResponseBrandDto> bulkUpdate(@RequestBody List<@Valid RequestBrandDto> requests) {
        return brandService.bulkUpdate(requests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
    }


    @GetMapping("/{id}/items")
    public PageResponseDto<ResponseItemDto> getItemsByBrand(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return brandService.getItemsByBrand(id, page, size);
    }
}
