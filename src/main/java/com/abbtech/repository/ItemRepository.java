package com.abbtech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.abbtech.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemRepository  extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);

    Page<Item> findByPriceBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Item> findByBrand_Id(Long brandId, Pageable pageable);

    @Transactional
    void deleteByBrand_Id(Long brandId);

    @Transactional
    void deleteByCategory_Id(Long categoryId);

}
