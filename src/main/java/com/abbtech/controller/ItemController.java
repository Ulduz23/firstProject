package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    public List<ResponseItemDto> getAll(@RequestHeader(value = "x-custom-header", required = false) String customHeader) {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseItemDto getByName(@PathVariable Long id) {
        return itemService.getById(id);
    }

    @GetMapping("/filter")
    public List<ResponseItemDto> getPriceRange(@RequestParam double min, @RequestParam double max) {
        return itemService.getPriceRange(min, max);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseItemDto add(@RequestBody @Valid RequestItemDto request) {
        return itemService.add(request);
    }

    @PutMapping("/bulk")
    public List<ResponseItemDto> bulkUpdate(@RequestBody List<@Valid RequestItemDto> request) {
        return itemService.bulkUpdate(request);
    }

    @PutMapping("/{id}")
    public ResponseItemDto updateById(@PathVariable Long id, @RequestBody @Valid RequestItemDto request) {
        return itemService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        itemService.deleteById(id);
    }

}
