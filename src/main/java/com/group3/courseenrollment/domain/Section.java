package com.group3.courseenrollment.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;

@Data
@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Valid
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Faculty> faculty;

	@Valid
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Offering offering;


	public Section() {
		
	}

}
