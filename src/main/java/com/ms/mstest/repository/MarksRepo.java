package com.ms.mstest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ms.mstest.model.Marks;

public interface MarksRepo extends MongoRepository<Marks, String>{
	
	@Query("{'sudent_id' : ?0}")
	public Optional<Marks> findByStudentId(String id)throws Exception;

}
