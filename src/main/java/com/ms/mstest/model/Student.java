package com.ms.mstest.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "students")
@Getter
@Setter
public class Student implements Serializable{

	@Id
	private String id;

	@Indexed(unique = true)
	@Field("roll_no")
	@JsonProperty("roll_no")
	private int rollno;

	@Field("first_name")
	@JsonProperty("first_name")
	private String firstname;

	@Field("last_name")
	@JsonProperty("last_name")
	private String lastname;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("tot_marks")
	private int totmarks;

}
