package com.group3.courseenrollment.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(max=60)
	private String name;
	@NotBlank
	@Size(max=15)
	private String title;

	public Faculty(String name, String title) {
		super();
		this.name = name;
		this.title = title;
	}

	public Faculty() {

	}



}
