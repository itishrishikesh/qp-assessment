package com.hrishi.groceries.service;

import com.hrishi.groceries.model.GroceryItem;
import com.hrishi.groceries.repository.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroceryItemService {
    private final GroceryItemRepository repository;
    public GroceryItem getGroceryById(Integer id) {
        return repository.findById(id).orElseThrow();
    }
    public GroceryItem saveGrocery(GroceryItem groceryItem) {
        return repository.save(groceryItem);
    }
    public void deleteGrocery(Integer id) {
        repository.deleteById(id);
    }
    public List<GroceryItem> getAllGroceries() {
        return repository.findAll();
    }
}
