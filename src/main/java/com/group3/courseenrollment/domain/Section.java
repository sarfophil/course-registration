package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.List;

public class Section {
	private long id;
	private List<Student> studentList = new ArrayList<>();
	private Faculty faculty;
	public Section(List<Student> studentList, Faculty faculty) {
		super();
		this.studentList = studentList;
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
	
		
}
