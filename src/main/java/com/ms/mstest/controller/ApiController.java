package com.ms.mstest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.mstest.model.Student;
import com.ms.mstest.service.ApiService;

@RestController
@RequestMapping("/")
public class ApiController {
	
	@Autowired
	public ApiService apiService;
	
	@PostMapping("/postdata")
	public Student postData(@RequestBody Student sd)throws Exception
	{
		return apiService.postData(sd);
	}
	
	@GetMapping("/getall")
	public List<Student> getAll()throws Exception
	{
	
		return apiService.getAll();
	}

}
