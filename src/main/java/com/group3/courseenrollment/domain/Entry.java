package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entry {
	private long id;
	private String name;
	private Date startDate;
	private Date enrolStartDate;
	private List<Student> studentList = new ArrayList<Student>();

	public Entry(String name, Date startDate, Date enrolStartDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.enrolStartDate = enrolStartDate;
	}

	public Entry() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEnrolStartDate() {
		return enrolStartDate;
	}

	public void setEnrolStartDate(Date enrolStartDate) {
		this.enrolStartDate = enrolStartDate;
	}

	public List<Student> getStudents() {
		return studentList;
	}

	public void setStudents(List<Student> students) {
		this.studentList = students;
	}

	public void addStudent(Student student) {
		studentList.add(student);
	}
}
