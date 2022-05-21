package com.mindtree.cabbooking.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.cabbooking.dto.BookingDTO;
import com.mindtree.cabbooking.exception.CabBookingException;
import com.mindtree.cabbooking.service.CabBookingService;

@RestController
@Validated
@RequestMapping(value = "/cabbooking")
public class CabBookingAPI {

	@Autowired
	CabBookingService cabService;

	@Autowired
	Environment environment;

	final Log LOGGER = LogFactory.getLog(CabBookingAPI.class);

	@GetMapping(value = "/bookings/{bookingType}")
	public ResponseEntity<List<BookingDTO>> getDetailsByBookingType(
			@PathVariable @Pattern(regexp = "([A-za-z]+)", message = "{Validator.INVALID_BOOKINGTYPE}") String bookingType)
			throws CabBookingException {
		List<BookingDTO> details = cabService.getDetailsByBookingType(bookingType);
		LOGGER.info("retrived data:" + details);
		return new ResponseEntity<>(details, HttpStatus.OK);
	}

	@PostMapping(value = "/booking")
	public ResponseEntity<String> bookCab(@RequestBody @Valid BookingDTO bookingDTO) throws CabBookingException {
		Integer id = cabService.bookCab(bookingDTO);
		String message = environment.getProperty("API.BOOKING_SUCCESS") + id;
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

}
