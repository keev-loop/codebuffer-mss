package com.codebuffer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.codebuffer.entity.Department;
import com.codebuffer.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

	
	private final DepartmentRepository _departmentRepo;
	
	
	// READ ALL
	public ResponseEntity<List<Department>> readAllDepartments() {
		log.info("Reading all Departments.");
		return new ResponseEntity<List<Department>>(_departmentRepo.findAll(), HttpStatus.OK);
	}
	
	
	// CREATE ONE
	public ResponseEntity<?> createDepartment(Department department) {
		try {
			log.info("Creating a new Department.");
			return new ResponseEntity<Department>(_departmentRepo.save(department), HttpStatus.CREATED);
		} catch(Exception err) {
			log.error("Error to try create new Department." + err.getMessage());
			return new ResponseEntity<>("Fail to try create Department: " + err.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// READ ONE BY ID
	public ResponseEntity<?> readOneDepartmentById(Long departmentId) {
		Optional<Department> department = _departmentRepo.findById(departmentId);
		if(department.isPresent()) {
			log.info("Reading a Department.");
			return new ResponseEntity<Department>(department.get(), HttpStatus.OK);
		} else {
			log.info("Department not found.");
			return new ResponseEntity<>("Department Not Found.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	// UPDATE ONE BY ID
	public ResponseEntity<?> updateOneDepartmentById(Long departmentId, Department newDepartment) {
		Optional<Department> oldDepartment = _departmentRepo.findById(departmentId);
		if(oldDepartment.isPresent()) {
			try {
				log.info("Updating a Department.");
				Department department = oldDepartment.get();
				
				department.setDepartmentName(newDepartment.getDepartmentName());
				department.setDepartmentAddress(newDepartment.getDepartmentAddress());
				department.setDepartmentCode(newDepartment.getDepartmentCode());
				
				return new ResponseEntity<Department>(_departmentRepo.save(department), HttpStatus.OK);
			} catch(Exception err) {
				log.error("Error to try update a Department." + err.getMessage());
				return new ResponseEntity<>("Fail to try update a Department: " + err.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			log.info("Department not found.");
			return new ResponseEntity<>("Department Not Found.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	// DELETE ONE BY ID
	public ResponseEntity<?> deleteOneDepartmentById(Long departmentId) {
		Optional<Department> department = _departmentRepo.findById(departmentId);
		if(department.isPresent()) {
			log.info("Deleting Department. ID: " + department.get().getDepartmentId());
			_departmentRepo.delete(department.get());
			return new ResponseEntity<>(department, HttpStatus.OK);
		} else {
			log.info("Fail to delete Department. Department Not Found. ID: " + department.get().getDepartmentId());
			return new ResponseEntity<>("Department Not Found!", HttpStatus.NOT_FOUND);
		}
	}
	
	
}
