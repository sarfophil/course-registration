package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToMany(mappedBy = "sectionList")
	private List<Student> studentList = new ArrayList<>();
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
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public void addStudent(Student student) {
		studentList.add(student);
	}
	
		
}
