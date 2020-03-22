package com.group3.courseenrollment.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
public class Offering {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String code;
	@Valid
	@OneToOne(cascade = CascadeType.PERSIST)
	private Course course;

	@Valid
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Block block;

	public Offering(Course course, Block block) {
		super();
		this.code = course.getCode()+"-"+block.getCode();
		this.course = course;
		this.block = block;
	}

	public Offering() {

	}


}
