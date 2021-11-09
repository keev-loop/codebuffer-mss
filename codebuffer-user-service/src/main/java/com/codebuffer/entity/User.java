package com.codebuffer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "user_id")
	private Long userId;
	
	@Column(nullable = false, name = "user_name")
	private String userName;
	
	@Column(nullable = false, name = "user_lastname")
	private String userLastname;
	
	@Column(nullable = false, name = "user_email")
	private String userEmail;
	
	@Column(nullable = true, name = "department_id")
	private Long departmentId;
	
}
