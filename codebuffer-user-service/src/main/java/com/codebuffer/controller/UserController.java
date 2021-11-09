package com.codebuffer.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebuffer.entity.User;
import com.codebuffer.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
	
	private final UserService _userServ;

	
	@GetMapping
	public ResponseEntity<?> readAll() {
		log.info("Request GET all...");
		return _userServ.readAll();
	}
	
	
	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody @Valid User user) {
		log.info("Request POST one...");
		return _userServ.createOne(user);
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> readOneById(@PathVariable(name = "userId") Long userId) {
		log.info("Request GET one...");
		return _userServ.readOneById(userId);
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateOneById(@PathVariable(name = "userId") Long userId, @RequestBody @Valid User newUser) {
		log.info("Request PUT one...");
		return _userServ.updateOneById(userId, newUser);
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteOneById(@PathVariable(name = "userId") Long userId) {
		log.info("Request DELETE one...");
		return _userServ.deleteOneById(userId);
	}
	
	
	@GetMapping("/yt={userId}")
	public ResponseEntity<?> readOneByIdYoutube(@PathVariable(name = "userId") Long userId) {
		log.info("Request GET one with Department...");
		return new ResponseEntity<>(_userServ.getUserWithDepartment(userId), HttpStatus.OK);
	}
	
	
}
