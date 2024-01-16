package com.myApp.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.myApp.DTO.AddressDTO;
import com.myApp.convert.Converter;
import com.myApp.entity.Address;
import com.myApp.exception.ProjectException;
import com.myApp.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepo;
	
	@Override
	public void addAddress(AddressDTO address) throws ProjectException {

			Address addEntity=Converter.convert(address);
			addressRepo.save(addEntity);	
	}
}
