package com.mindtree.cabbooking.utility;

import org.modelmapper.ModelMapper;

public class Conversion<T,E> {

	ModelMapper modelMapper;

	public E getConversionObject(Class<E>c,T source)
	{
		return modelMapper.map(source, c);
	}

}
