package com.mindtree.cabbooking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.cabbooking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
	List<Booking> findByBookingType(String bookingType);
}
