package com.hrishi.groceries.repository;

import com.hrishi.groceries.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
