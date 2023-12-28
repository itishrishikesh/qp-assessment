package com.hrishi.groceries.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;
    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "grocery_item_id", referencedColumnName = "id")
    private GroceryItem item;
    private Integer bookingQuantity;
    private Integer finalPrice;
    private Boolean isProcessed;
}
