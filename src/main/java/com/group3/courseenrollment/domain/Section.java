package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Enrollment> enrollmentList = new ArrayList<>();
	@OneToOne(cascade = CascadeType.PERSIST)
	private Faculty faculty;
	public Section(Faculty faculty) {
		super();
		this.faculty = faculty;
	}
	
	public Section() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Enrollment> getStudentList() {
		return enrollmentList;
	}
	public void setEnrollment(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public void addStudent(Enrollment enrollment) {
		enrollmentList.add(enrollment);
	}
	
		
}
