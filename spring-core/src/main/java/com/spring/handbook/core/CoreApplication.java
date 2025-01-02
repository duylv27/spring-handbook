package com.spring.handbook.core;

import com.spring.handbook.core.repository.StudentRepository;
import com.spring.handbook.core.service.StudentComponent;
import com.spring.handbook.core.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			StudentRepository studentRepository,
			StudentService studentService,
			StudentComponent studentComponent
	) {
		return args -> {
			studentComponent.test();
			studentService.test();
			var student = studentRepository.findAll();
			System.out.println(student);
		};
	}

}
