package com.codebuffer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codebuffer.entity.User;
import com.codebuffer.repository.UserRepository;
import com.codebuffer.vo.Department;
import com.codebuffer.vo.ResponseTemplateVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository _userRepo;
	private final RestTemplate _restTemplate;
	
	// READ ALL
	public ResponseEntity<List<User>> readAll() {
		log.info("...Reading all User.");
		return new ResponseEntity<>(_userRepo.findAll(), HttpStatus.OK);
	}
	
	
	// CREATE ONE
	public ResponseEntity<?> createOne(User user) {
		try {
			log.info("...Creating new User.");
			return new ResponseEntity<User>(_userRepo.save(user), HttpStatus.CREATED);
		} catch(Exception err) {
			log.error("...Fail to try create new User. " + err.getMessage());
			return new ResponseEntity<>("Error to try create new User." + err.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	// READ ONE BY ID
	public ResponseEntity<?> readOneById(Long userId) {
		Optional<User> user = _userRepo.findById(userId);
		if(user.isPresent()) {
			log.info("...Reading one User.");
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			log.info("...User Not Found.");
			return new ResponseEntity<>("User Not Found.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	// UPDATE ONE BY ID
	public ResponseEntity<?> updateOneById(Long userId, User newUser) {
		Optional<User> oldUser = _userRepo.findById(userId);
		if(oldUser.isPresent()) {
			try {
				User user = oldUser.get();
				
				user.setUserName(newUser.getUserName());
				user.setUserLastname(newUser.getUserLastname());
				user.setUserEmail(newUser.getUserEmail());
				user.setDepartmentId(newUser.getDepartmentId());
				
				log.info("...Updating one User.");
				return new ResponseEntity<User>(_userRepo.save(user), HttpStatus.OK);
			} catch(Exception err) {
				log.error("...Fail to try update User. " + err.getMessage());
				return new ResponseEntity<>("Error to try update User." + err.getMessage(), HttpStatus.BAD_REQUEST);
			}
		} else {
			log.info("...User Not Found.");
			return new ResponseEntity<>("User Not Found.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	// DELETE ONE BY ID
	public ResponseEntity<?> deleteOneById(Long userId) {
		Optional<User> user = _userRepo.findById(userId);
		if(user.isPresent()) {
			_userRepo.delete(user.get());
			log.info("...Deleting one User.");
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			log.info("...User Not Found.");
			return new ResponseEntity<>("User Not Found.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	//
	public ResponseTemplateVo getUserWithDepartment(Long userId) {
		ResponseTemplateVo vo = new ResponseTemplateVo();
		Optional<User> userOjt = _userRepo.findById(userId);
		User user = userOjt.get();
		
		Department department = _restTemplate.getForObject("http://DEPARTMENT-SERVICE/api/v1/departments/"+user.getDepartmentId(), Department.class);
		
		vo.setUser(user);
		vo.setDepartment(department);
		
		return vo;
	}
	
	
}
