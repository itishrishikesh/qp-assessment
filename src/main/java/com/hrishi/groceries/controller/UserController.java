package com.hrishi.groceries.controller;

import com.hrishi.groceries.model.AppUser;
import com.hrishi.groceries.model.Booking;
import com.hrishi.groceries.model.GroceryItem;
import com.hrishi.groceries.model.SecurityUserDetails;
import com.hrishi.groceries.service.AppUserService;
import com.hrishi.groceries.service.BookingService;
import com.hrishi.groceries.service.GroceryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AppUserService appUserService;
    private final GroceryItemService groceryItemService;
    private final BookingService bookingService;

    @GetMapping("/{userId}")
    public AppUser getUser(@PathVariable("userId") Integer userId) {
        return appUserService.getUserById(userId);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser register(@RequestBody AppUser appUser) {
        return appUserService.save(appUser);
    }

    @PostMapping("/remove/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> register(@PathVariable Integer userId) {
        try {
            appUserService.deleteUser(userId);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            appUserService.deleteUser(userId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/items")
    public List<GroceryItem> allAvailableItems() {
        return groceryItemService.getAllGroceries().stream().filter(item -> item.getAvailableQuantity() > 0).toList();
    }

    @PostMapping("/item/{itemId}/book")
    public ResponseEntity<Booking> book(@PathVariable Integer itemId, @AuthenticationPrincipal SecurityUserDetails user) {
        try {
            GroceryItem groceryItem = groceryItemService.getGroceryById(itemId);
            if(groceryItem.getAvailableQuantity() < 0) {
                // If grocery item is available then that item doesn't exist for the user to book.
                throw new NoSuchElementException();
            }
            Booking booking = new Booking();
            booking.setUser(AppUser.builder().id(user.getUserId()).username(user.getUsername()).build());
            booking.setItem(groceryItem);
            booking.setBookingQuantity(booking.getBookingQuantity() == null ? 1 : booking.getBookingQuantity() + 1);
            booking.setFinalPrice(groceryItem.getPrice().intValue());
            booking.setIsProcessed(false);
            return ResponseEntity.ok(bookingService.book(booking));
        } catch (Exception exception){
            log.info("Exception occurred while booking! {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/item/booking/{bookingId}/process")
    public ResponseEntity<Booking> process(@PathVariable Integer bookingId) {
        try {
            return ResponseEntity.ok(bookingService.process(bookingId));
        } catch (Exception exception){
            log.info("Exception occurred while processing a booking! {}", exception.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
