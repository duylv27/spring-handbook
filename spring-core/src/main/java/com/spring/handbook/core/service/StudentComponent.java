package com.spring.handbook.core.service;

import com.spring.handbook.core.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class StudentComponent {

    private final StudentRepository studentRepository;

    public StudentComponent(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void test(){}

}
