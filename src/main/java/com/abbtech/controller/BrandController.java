package com.abbtech.controller;

import com.abbtech.dto.request.RequestBrandDto;
import com.abbtech.dto.response.ResponseBrandDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<ResponseBrandDto> getAll() {
        return brandService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseBrandDto getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseBrandDto add(@RequestBody RequestBrandDto request) {
        return brandService.add(request);
    }

    @PutMapping("/{id}")
    public ResponseBrandDto updateById(@PathVariable Long id, @RequestBody RequestBrandDto request) {
        return brandService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
    }


    @GetMapping("/{id}/items")
    public List<ResponseItemDto> getItemsByBrand(@PathVariable Long id) {
        return brandService.getItemsByBrand(id);
    }
}

