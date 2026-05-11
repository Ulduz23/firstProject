package com.abbtech.productservice.brand.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abbtech.productservice.common.annotation.CustomTransactionAnnotation;
import com.abbtech.productservice.brand.dto.RequestBrandDto;
import com.abbtech.productservice.common.dto.PageResponseDto;
import com.abbtech.productservice.brand.dto.ResponseBrandDto;
import com.abbtech.productservice.item.dto.ResponseItemDto;
import com.abbtech.productservice.brand.service.BrandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseBrandDto> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Positive int size) {
        return brandService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public ResponseBrandDto getById(@PathVariable @Positive Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    @CustomTransactionAnnotation
    public ResponseBrandDto add(@RequestBody @Valid RequestBrandDto request) {
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    @CustomTransactionAnnotation
    public ResponseBrandDto updateById(@PathVariable @Positive Long id, @RequestBody @Valid RequestBrandDto request) {
        return brandService.updateById(id, request);
    }

    @PutMapping("/bulk")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    @CustomTransactionAnnotation
    public List<ResponseBrandDto> bulkUpdate(@RequestBody List<@Valid RequestBrandDto> requests) {
        return brandService.bulkUpdate(requests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    @CustomTransactionAnnotation
    public void deleteById(@PathVariable @Positive Long id) {
        brandService.deleteById(id);
    }


    @GetMapping("/{id}/items")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    @CustomTransactionAnnotation(readOnlyTrue = true)
    public PageResponseDto<ResponseItemDto> getItemsByBrand(
            @PathVariable @Positive Long id,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Positive int size) {
        return brandService.getItemsByBrand(id, page, size);
    }
}
