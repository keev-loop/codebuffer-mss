package com.codebuffer.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebuffer.entity.Department;
import com.codebuffer.service.DepartmentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class DepartmentController {

	
	private final DepartmentService _departmentServ;
	
	
	@GetMapping
	public ResponseEntity<?> readAll() {
		log.info("Request GET all...");
		return _departmentServ.readAllDepartments();
	}
	
	
	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody @Valid Department department) {
		log.info("Request POST one...");
		return _departmentServ.createDepartment(department);
	}
	
	
	@GetMapping("/{departmentId}")
	public ResponseEntity<?> readOneById(@PathVariable(name = "departmentId") Long departmentId) {
		log.info("Request GET one...");
		return _departmentServ.readOneDepartmentById(departmentId);
	}
	
	
	@PutMapping("/{departmentId}")
	public ResponseEntity<?> updateOneById(@PathVariable(name = "departmentId") Long departmentId, @RequestBody @Valid Department newDepartment) {
		log.info("Request PUT one...");
		return _departmentServ.updateOneDepartmentById(departmentId, newDepartment);
	}
	
	
	@DeleteMapping("/{departmentId}")
	public ResponseEntity<?> deleteOneById(@PathVariable(name = "departmentId") Long departmentI) {
		log.info("Request DELETE one...");
		return _departmentServ.deleteOneDepartmentById(departmentI);
	}
	

}
