package com.hrishi.groceries;

import com.hrishi.groceries.model.AppUser;
import com.hrishi.groceries.model.GroceryItem;
import com.hrishi.groceries.service.AppUserService;
import com.hrishi.groceries.service.GroceryItemService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GroceriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroceriesApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppUserService appUserService, GroceryItemService groceryItemService) {
        return args -> {
            AppUser master = AppUser.builder()
                    .username("master")
                    .password("Master@1234!")
                    .active(true)
                    .role("ROLE_ADMIN")
                    .createAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            appUserService.save(master);
            GroceryItem arhar = GroceryItem.builder()
                    .availableQuantity(200)
                    .price(97.02)
                    .name("Arhar Dal")
                    .category("Lentils")
                    .unit("kilograms")
                    .build();
            GroceryItem channa = GroceryItem.builder()
                    .availableQuantity(0)
                    .price(26.03)
                    .name("Channa Dal")
                    .category("Lentils")
                    .unit("kilograms")
                    .build();
            groceryItemService.saveGrocery(arhar);
            groceryItemService.saveGrocery(channa);
        };
    }
}
