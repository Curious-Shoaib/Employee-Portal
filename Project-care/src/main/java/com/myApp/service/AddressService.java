package com.myApp.service;

import com.myApp.DTO.AddressDTO;
import com.myApp.exception.ProjectException;


public interface AddressService {

	public void addAddress(AddressDTO address) throws ProjectException;
	
}
