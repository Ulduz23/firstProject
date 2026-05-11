package com.abbtech.productservice.item.controller;

import com.abbtech.productservice.item.dto.RequestItemDto;
import com.abbtech.productservice.common.dto.PageResponseDto;
import com.abbtech.productservice.item.dto.ResponseItemDto;
import com.abbtech.productservice.item.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/items")
@RequiredArgsConstructor
@Validated
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public PageResponseDto<ResponseItemDto> getAll(
            @RequestHeader(value = "x-custom-header", required = false) String customHeader,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Positive int size) {
        return itemService.getAll(page, size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseItemDto getByName(@PathVariable @Positive Long id) {
        return itemService.getById(id);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public PageResponseDto<ResponseItemDto> getPriceRange(
            @RequestParam @DecimalMin(value = "0.0") double min,
            @RequestParam @DecimalMin(value = "0.0") double max,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "10") @Positive int size) {
        return itemService.getPriceRange(min, max, page, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseItemDto add(@RequestBody @Valid RequestItemDto request) {
        return itemService.add(request);
    }

    @PutMapping("/bulk")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public List<ResponseItemDto> bulkUpdate(@RequestBody List<@Valid RequestItemDto> request) {
        return itemService.bulkUpdate(request);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE')")
    public ResponseItemDto updateById(@PathVariable @Positive Long id, @RequestBody @Valid RequestItemDto request) {
        return itemService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE')")
    public void delete(@PathVariable @Positive Long id) {
        itemService.deleteById(id);
    }

}
