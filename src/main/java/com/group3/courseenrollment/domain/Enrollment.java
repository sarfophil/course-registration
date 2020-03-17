package com.group3.courseenrollment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue
	private long id;
	
	private Student student;
	private Section section;
	
	public Enrollment(Student student, Section section) {
		super();
		this.student = student;
		this.section = section;
	}
	
	
	public Enrollment() {
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	
	
}
