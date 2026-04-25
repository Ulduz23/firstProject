package com.abbtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.abbtech.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ItemRepository  extends JpaRepository<Item, Long> {
    Optional<Item> findByName(String name);

    List<Item> findByPriceBetween(BigDecimal min, BigDecimal max);

    @Transactional
    void deleteByBrand_Id(Long brandId);

    @Transactional
    void deleteByCategory_Id(Long categoryId);

}
