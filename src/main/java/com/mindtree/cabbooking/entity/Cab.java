package com.mindtree.cabbooking.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Cab {
	@Id
	private Integer cabNo;
	private String modelName;
	private Long driverPhoneNo;
	private String availability;

}
