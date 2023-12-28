package com.hrishi.groceries.controller;

import com.hrishi.groceries.model.GroceryItem;
import com.hrishi.groceries.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class GroceryController {
    private final GroceryItemService service;

    @PostMapping("/item")
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryItem addItem(@RequestBody GroceryItem item) {
        return service.saveGrocery(item);
    }

    @PutMapping("/item")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GroceryItem> updateItem(@RequestBody GroceryItem item) {
        if (service.getGroceryById(item.getId()) != null)
            return ResponseEntity.ok(service.saveGrocery(item));
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/inventory")
    @ResponseStatus(HttpStatus.OK)
    public List<GroceryItem> getItems() {
        return service.getAllGroceries();
    }

    @DeleteMapping("/item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable Integer itemId) {
        service.deleteGrocery(itemId);
    }

    @GetMapping("/item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public GroceryItem getItem(@PathVariable Integer itemId) {
        return service.getGroceryById(itemId);
    }
}
