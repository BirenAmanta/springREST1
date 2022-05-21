package com.mindtree.cabbooking.utility;

import org.modelmapper.ModelMapper;

public class Conversion<T,E> {


	public E getConversionObject(T source,Class<E>c)
	{
		return new ModelMapper().map(source, c);
	}

}
