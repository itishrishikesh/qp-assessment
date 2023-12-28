package com.hrishi.groceries.repository;

import com.hrishi.groceries.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {
}
