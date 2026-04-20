package com.abbtech.repository;

import com.abbtech.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository  extends JpaRepository<Item, Long> {

}
