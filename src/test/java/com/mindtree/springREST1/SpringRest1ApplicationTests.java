package com.mindtree.springREST1;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.mindtree.cabbooking.dto.BookingDTO;
import com.mindtree.cabbooking.dto.CabDTO;
import com.mindtree.cabbooking.entity.Booking;
import com.mindtree.cabbooking.exception.CabBookingException;
import com.mindtree.cabbooking.repository.BookingRepository;
import com.mindtree.cabbooking.repository.CabRepository;
import com.mindtree.cabbooking.service.CabBookingService;
import com.mindtree.cabbooking.service.CabBookingServiceImpl;

@SpringBootTest(classes = SpringRest1ApplicationTests.class)
class SpringRest1ApplicationTests {

	@Mock
	CabRepository cabRepo;
	
	@Mock
	BookingRepository bookingRepo;
	
	@InjectMocks
	CabBookingService cabService=new  CabBookingServiceImpl();
	
	@Test
	public void bookCabInvalidCabNoTest()
	{
		Integer id=1234;
		BookingDTO data=new BookingDTO();
		data.setCabDTO(new CabDTO());
		data.getCabDTO().setCabNo(id);
		Mockito.when(cabRepo.findById(id)).thenReturn(Optional.ofNullable(null));
		CabBookingException e=Assertions.assertThrows(CabBookingException.class, ()->cabService.bookCab(data));
		Assertions.assertEquals("Service.CAB_NOT_FOUND",e.getMessage());
	}
	
	@Test
	public void getDetailsByBookingTypeNoDetailsFound()
	{
		String type="NA";
		Mockito.when(bookingRepo.findByBookingType(type)).thenReturn(new ArrayList<Booking>());
		CabBookingException ex=Assertions.assertThrows(CabBookingException.class,()->cabService.getDetailsByBookingType(type));
		Assertions.assertEquals("Service.NO_DETAILS_FOUND",ex.getMessage());
		
		
	}
	
	

}
