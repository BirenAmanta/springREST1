package com.mindtree.cabbooking.utility;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorInfo {
	private int errorCode;
	private String errorMessage;
	private LocalDate timeStamp;
	
}
