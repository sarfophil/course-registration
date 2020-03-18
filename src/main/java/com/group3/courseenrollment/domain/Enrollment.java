package com.group3.courseenrollment.domain;


import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate enrolStartDate;
	private LocalDate enrolEndDate;

	public Enrollment(LocalDate enrolStartDate, LocalDate enrolEndDate) {
		super();
		this.enrolStartDate = enrolStartDate;
		this.enrolEndDate = enrolEndDate;
	}

	public Enrollment() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getEnrolStartDate() {
		return enrolStartDate;
	}

	public void setEnrolStartDate(LocalDate enrolStartDate) {
		this.enrolStartDate = enrolStartDate;
	}

	public LocalDate getEnrolEndDate() {
		return enrolEndDate;
	}

	public void setEnrolEndDate(LocalDate enrolEndDate) {
		this.enrolEndDate = enrolEndDate;
	}
}




