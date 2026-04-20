package com.abbtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abbtech.model.Item;

public interface ItemRepository  extends JpaRepository<Item, Long> {

}
