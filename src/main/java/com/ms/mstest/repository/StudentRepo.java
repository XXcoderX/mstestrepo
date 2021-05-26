package com.ms.mstest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ms.mstest.model.Student;

public interface StudentRepo extends MongoRepository<Student, String>{

}
