package com.myApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myApp.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
