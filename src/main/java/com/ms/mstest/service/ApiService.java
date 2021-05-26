package com.ms.mstest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ms.mstest.model.Marks;
import com.ms.mstest.model.Student;
import com.ms.mstest.repository.MarksRepo;
import com.ms.mstest.repository.StudentRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApiService {
	
	@Autowired
	public StudentRepo studentRepo;
	
	@Autowired
	public MarksRepo marksRepo;
	
	public Student postData(Student sd)throws Exception
	{
		Student savedData = studentRepo.save(sd);
		marksRepo.save(new Marks(savedData.getId(), sd.getTotmarks()));
		log.info("saved");
		return savedData;
	}
	
	public List<Student> getAll()throws Exception
	{
		List<Student> sd = studentRepo.findAll();
		sd.forEach(s -> {
			Optional<Marks> m = null;
			try {
				m = marksRepo.findByStudentId(s.getId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			s.setTotmarks(m.orElse(new Marks()).getTotalMarks());
		});
		return sd;
	}
}
