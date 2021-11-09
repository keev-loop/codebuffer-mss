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
@Table(name = "DEPARTMENT")
public class Department {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "department_id")
	private Long departmentId;
	
	@Column(nullable = false, name = "department_name")
	private String departmentName;
	
	@Column(nullable = false, name = "department_address")
	private String departmentAddress;
	
	@Column(nullable = false, name = "department_code")
	private String departmentCode;
	
	
}
