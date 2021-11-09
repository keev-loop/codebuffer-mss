package com.codebuffer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CodebufferDepartmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodebufferDepartmentServiceApplication.class, args);
	}

}
