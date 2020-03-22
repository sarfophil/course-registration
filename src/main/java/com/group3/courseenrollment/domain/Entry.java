package com.group3.courseenrollment.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Size(max=45)
	private String name;
	@FutureOrPresent
	private LocalDate startDate;
	@FutureOrPresent
	private LocalDate enrolStartDate;
	@Future
	private LocalDate enrolEndDate;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Student> studentList = new ArrayList<Student>();
	private Boolean hasWriteAccess;

	public Entry(String name, LocalDate startDate, LocalDate enrolStartDate,LocalDate enrolEndDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.enrolStartDate = enrolStartDate;
		this.enrolEndDate = enrolEndDate;
		this.hasWriteAccess = true;
	}

	public Entry() {

	}


}
