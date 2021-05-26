package com.ms.mstest.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "marks")
@Getter
@Setter
@NoArgsConstructor
public class Marks implements Serializable{

	@Id
	private String id;

	@Field("sudent_id")
	@Indexed(unique = true)
	private String sudentId;

	@Field("tot_marks")
	private int totalMarks;

	public Marks(String sudentId, int totalMarks) {
		super();
		this.sudentId = sudentId;
		this.totalMarks = totalMarks;
	}	
	
	public Marks(String sudentId) {
		this.sudentId = sudentId;
	}	

}
