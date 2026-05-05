package com.abbtech.controller;

import com.abbtech.annotation.CustomTransactionAnnotation;
import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.BrandService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
