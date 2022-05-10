package com.mindtree.cabbooking.service;

import java.util.List;

import com.mindtree.cabbooking.dto.BookingDTO;
import com.mindtree.cabbooking.exception.CabBookingException;

public interface CabBookingService {

	  List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException;
	  Integer bookCab(BookingDTO bookingDTO) throws CabBookingException ;

}
