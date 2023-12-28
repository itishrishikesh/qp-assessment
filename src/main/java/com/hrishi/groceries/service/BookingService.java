package com.hrishi.groceries.service;

import com.hrishi.groceries.model.Booking;
import com.hrishi.groceries.model.GroceryItem;
import com.hrishi.groceries.repository.BookingRepository;
import com.hrishi.groceries.repository.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final GroceryItemRepository groceryItemRepository;
    public Booking book(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking process(Integer bookingId) {
        Booking book = bookingRepository.findById(bookingId).orElseThrow();
        GroceryItem groceryItem = groceryItemRepository.findById(book.getItem().getId()).orElseThrow();
        groceryItem.setAvailableQuantity(groceryItem.getAvailableQuantity() - 1);
        groceryItemRepository.save(groceryItem);
        book.setIsProcessed(true);
        return book;
    }

    public Booking getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }
}
