package com.mindtree.cabbooking.utility;

import com.mindtree.cabbooking.exception.CabBookingException;

public class Validator {

	public boolean Validate(Long phoneNo) throws CabBookingException
	{
		if(validatePhoneNo(phoneNo))
		{
			return true;
		}
		else
		{
			throw new CabBookingException("Validator.INVALID_PHONENO");
		}
	}

	private boolean validatePhoneNo(Long phoneNo) {
		String no=Long.toString(phoneNo);
		if(no.matches("([6|9]){1}([0-9]{9})"))return true;
		return false;
	}

}
