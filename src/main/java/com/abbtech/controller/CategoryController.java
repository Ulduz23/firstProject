package com.abbtech.controller;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.service.CategoryService;
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
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public PageResponseDto<ResponseCategoryDto> getAll(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Positive int size) {
        return categoryService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseCategoryDto getById(@PathVariable @Positive Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseCategoryDto add(@RequestBody @Valid RequestCategoryDto request) {
        return categoryService.add(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseCategoryDto updateById(@PathVariable @Positive Long id, @RequestBody @Valid RequestCategoryDto request) {
        return categoryService.updateById(id, request);
    }

    @PutMapping("/bulk")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public List<ResponseCategoryDto> bulkUpdate(@RequestBody List<@Valid RequestCategoryDto> requests) {
        return categoryService.bulkUpdate(requests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public void deleteById(@PathVariable @Positive Long id) {
        categoryService.deleteById(id);
    }
}
