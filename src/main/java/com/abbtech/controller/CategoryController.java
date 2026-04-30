package com.abbtech.controller;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.PageResponseDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public PageResponseDto<ResponseCategoryDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto add(@RequestBody @Valid RequestCategoryDto request) {
        return categoryService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto updateById(@PathVariable Long id, @RequestBody @Valid RequestCategoryDto request) {
        return categoryService.updateById(id, request);
    }

    @PutMapping("/bulk")
    public List<ResponseCategoryDto> bulkUpdate(@RequestBody List<@Valid RequestCategoryDto> requests) {
        return categoryService.bulkUpdate(requests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
