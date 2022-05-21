package com.mindtree.cabbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Booking {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer bookingId;
	private String customerName;
	private Long phoneNo;
	private String bookingType;
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "cab_no")
	private Cab cab;

}
