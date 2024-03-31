package com.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.exception.DataNotFoundException;
import com.crud.models.Employee;
import com.crud.repository.EmployeeResposity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	@Autowired
	private EmployeeResposity employeeResposity;

	// Save Employee
	@PostMapping("/save")
	public ResponseEntity<Object> create(@RequestBody @Valid Employee employee) {
		Employee savedEmployee = employeeResposity.save(employee);
		System.out.println("Saved Employee - Name: " + savedEmployee.getName() + ", Email: " + savedEmployee.getEmail());
		return ResponseEntity.ok().body(savedEmployee);
	}

	// Get Employees
	@GetMapping("/get")
	public ResponseEntity<Object> getAll() {
		Iterable<Employee> employees = employeeResposity.findAll();
		return ResponseEntity.ok().body(employees);
	}

	// Get employee With Id
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getById(@PathVariable int id) {
		Employee employee = employeeResposity.findById(id).get();
		return ResponseEntity.ok().body(employee);
	}

	// Update Employee by Id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable int id, @RequestBody @Valid Employee employee) {
	    Employee employees = employeeResposity.findById(id).orElse(null);
	    if (employees != null) {
	        employees.setName(employee.getName());
	        employees.setEmail(employee.getEmail());
	        employeeResposity.save(employees);
	        return ResponseEntity.ok().body(employees);
	    } else {
//	    	return ResponseEntity.badRequest().body("Employee not found");
	    	throw new DataNotFoundException("Employee Not Found for this id "+id);
	    }
	}
	
	// Delete Employee by Id
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
		Employee employee = employeeResposity.findById(id).orElse(null);
		if (employee == null) {
			return ResponseEntity.badRequest().body("Employee not found");
        }
		employeeResposity.deleteById(id);
		return ResponseEntity.ok().body("Employee deleted Succesfully");
	}
	
	// DeleteAll Employee
	@DeleteMapping("/deleteall")
    public ResponseEntity<Object> deleteAll() {
        employeeResposity.deleteAll();
        return ResponseEntity.ok().body("Employee deleted Succesfully");
    }
}
