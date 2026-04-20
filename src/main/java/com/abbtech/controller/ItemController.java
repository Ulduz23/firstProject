package com.abbtech.controller;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


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
    public ResponseItemDto add(@RequestBody RequestItemDto request) {
        return itemService.add(request);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAll(@RequestBody List<RequestItemDto> request) {
        itemService.saveAll(request);
    }

    @PutMapping("/{name}")
    public ResponseItemDto updateByName(@PathVariable String name, @RequestBody RequestItemDto request) {
        return itemService.updateByName(name, request);
    }

    @PatchMapping("/{name}")
    public ResponseItemDto partialUpdateByName(@PathVariable String name, @RequestParam String itemDescription) {

        return itemService.partialUpdateByName(name, itemDescription);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.deleteById(id);
    }

}
