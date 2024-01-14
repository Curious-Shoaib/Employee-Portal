package com.myApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myApp.entity.Demand;
import com.myApp.entity.Employee;

public interface DemandRepository extends JpaRepository<Demand, Integer> {
}
