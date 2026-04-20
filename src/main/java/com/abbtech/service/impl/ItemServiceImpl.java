package com.abbtech.service.impl;

import com.abbtech.dto.request.RequestItemDto;
import com.abbtech.dto.response.ResponseItemDto;
import com.abbtech.repository.BrandRepository;
import com.abbtech.repository.ItemRepository;
import com.abbtech.service.ItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    private final BrandRepository brandRepository;

    public ItemServiceImpl(ItemRepository itemRepository, BrandRepository brandRepository) {
        this.itemRepository = itemRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    @Transactional
    public ResponseItemDto add(RequestItemDto request) {
        return null;
    }

    @Override
    @Transactional
    public void saveAll(List<RequestItemDto> requestItems) {
//        itemRepository.saveAll(requestItems
//                .stream()
//                .map(item -> {
//                    Item newItem = new Item(item.getName(), item.getPrice(), item.getImage(), item.getDescription());
//                    newItem.setIsActive(true);
//                    newItem.setIsDeleted(false);
//                    return newItem;
//                })
//                .toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseItemDto> getAll() {
//        return itemRepository.getAll()
//                .stream()
//                .map(item -> new ResponseItemDto(
//                        item.getId() == null ? null : item.getId().longValue(),
//                        item.getName(),
//                        item.getPrice(),
//                        item.getImage(),
//                        item.getDescription()))
//                .toList();

        return null;
    }

    public ResponseItemDto getById(Long id) {

        return null;

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public ResponseItemDto updateByName(String name, RequestItemDto requestItemDto) {

        return null;
    }

    @Override
    public List<ResponseItemDto> getPriceRange(double min, double max) {

        return null;
    }

    @Override
    public ResponseItemDto partialUpdateByName(String name, String itemDescription) {

        return null;
    }


}
