package com.mindtree.cabbooking.service;

import java.util.List;

import com.mindtree.cabbooking.dto.BookingDTO;
import com.mindtree.cabbooking.exception.CabBookingException;
import com.mindtree.cabbooking.repository.BookingRepository;
import com.mindtree.cabbooking.repository.CabRepository;

public class CabBookingSercieImpl implements CabBookingService {

	CabRepository cabRepo;
	
	BookingRepository bookingRepo;
	@Override
	public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException {
		// TODO Auto-generated method stub
		return null;
	}

}
