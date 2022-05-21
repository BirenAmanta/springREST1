package com.mindtree.cabbooking.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingDTO {
	private Integer bookingId;
	@NotNull
	private String customerName;
	@NotNull
	private Long phoneNo;
	@NotNull
	private String bookingType;
	@NotNull
	private CabDTO cabDTO;

}
