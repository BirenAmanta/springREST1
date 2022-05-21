package com.mindtree.cabbooking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.cabbooking.dto.BookingDTO;
import com.mindtree.cabbooking.dto.CabDTO;
import com.mindtree.cabbooking.entity.Booking;
import com.mindtree.cabbooking.entity.Cab;
import com.mindtree.cabbooking.exception.CabBookingException;
import com.mindtree.cabbooking.repository.BookingRepository;
import com.mindtree.cabbooking.repository.CabRepository;
import com.mindtree.cabbooking.utility.Conversion;
import com.mindtree.cabbooking.utility.Validator;

@Service
@Transactional
public class CabBookingServiceImpl implements CabBookingService {

	@Autowired
	CabRepository cabRepo;
	@Autowired
	BookingRepository bookingRepo;

	@Override
	public List<BookingDTO> getDetailsByBookingType(String bookingType) throws CabBookingException {
		List<Booking> data = bookingRepo.findByBookingType(bookingType);
		if (data.isEmpty()) {
			throw new CabBookingException("Service.NO_DETAILS_FOUND");
		}
		System.out.println(data.get(0).getCab());
		return data.stream().map((temp) -> {

			BookingDTO bookingDTO = new Conversion<Booking, BookingDTO>().getConversionObject(temp, BookingDTO.class);
			bookingDTO.setCabDTO(new Conversion<Cab, CabDTO>().getConversionObject(temp.getCab(), CabDTO.class));
			return bookingDTO;

		}).collect(Collectors.toList());
	}

	@Override
	public Integer bookCab(BookingDTO bookingDTO) throws CabBookingException {
		Optional<Cab> cabData = cabRepo.findById(bookingDTO.getCabDTO().getCabNo());
		Cab details = cabData.orElseThrow(() -> new CabBookingException("Service.CAB_NOT_FOUND"));
		if(details.getAvailability().equalsIgnoreCase("No"))
		{
			throw new CabBookingException("Service.CAB_NOT_AVAILABLE");
		}
		new Validator().Validate(bookingDTO.getPhoneNo());
		Booking bookingData = new Conversion<BookingDTO, Booking>().getConversionObject(bookingDTO, Booking.class);
		bookingData.setCab(details);
		bookingData.setCab(details);
		bookingRepo.save(bookingData);
		details.setAvailability("No");
		return bookingData.getBookingId();
	}

}
