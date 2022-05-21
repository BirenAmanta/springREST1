package com.mindtree.cabbooking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CabDTO {

	private Integer cabNo;
	private String modelName;
	private Long driverPhoneNo;
	private String availability;

}
