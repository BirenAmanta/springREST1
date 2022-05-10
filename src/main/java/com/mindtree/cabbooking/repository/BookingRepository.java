package com.mindtree.cabbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.mindtree.cabbooking.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {

}
