package com.myApp.Service;

import com.myApp.DTO.AddressDTO;
import com.myApp.exception.ProjectException;


public interface AddressService {

	public void addAddress(AddressDTO address) throws ProjectException;
	
}
