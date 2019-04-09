package com.pebstone.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pebstone.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
 
    Employee findByUserName(String username);
}