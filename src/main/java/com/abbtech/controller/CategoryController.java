package com.abbtech.controller;

import com.abbtech.dto.request.RequestCategoryDto;
import com.abbtech.dto.response.ResponseCategoryDto;
import com.abbtech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<ResponseCategoryDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseCategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseCategoryDto add(@RequestBody RequestCategoryDto request) {
        return categoryService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseCategoryDto updateById(@PathVariable Long id, @RequestBody RequestCategoryDto request) {
        return categoryService.updateById(id, request);
    }

    @PutMapping("/bulk")
    public List<ResponseCategoryDto> bulkUpdate(@RequestBody List<RequestCategoryDto> requests) {
        return categoryService.bulkUpdate(requests);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
