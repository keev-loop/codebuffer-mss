package com.codebuffer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebuffer.entity.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
