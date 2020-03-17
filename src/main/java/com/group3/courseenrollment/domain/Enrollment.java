package com.group3.courseenrollment.domain;

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
	@Temporal(TemporalType.DATE)
	private Date enrolStartDate;
	@Temporal(TemporalType.DATE)
	private Date enrolEndDate;

	public Enrollment(Date enrolStartDate, Date enrolEndDate) {
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

	public Date getEnrolStartDate() {
		return enrolStartDate;
	}

	public void setEnrolStartDate(Date enrolStartDate) {
		this.enrolStartDate = enrolStartDate;
	}

	public Date getEnrolEndDate() {
		return enrolEndDate;
	}

	public void setEnrolEndDate(Date enrolEndDate) {
		this.enrolEndDate = enrolEndDate;
	}

}
