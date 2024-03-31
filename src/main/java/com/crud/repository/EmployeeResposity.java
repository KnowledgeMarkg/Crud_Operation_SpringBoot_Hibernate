package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.models.Employee;

@Repository
public interface EmployeeResposity extends JpaRepository<Employee, Integer> {

}
