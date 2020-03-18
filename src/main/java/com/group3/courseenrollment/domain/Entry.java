package com.group3.courseenrollment.domain;

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

@Entity
public class Entry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date enrolStartDate;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Student> studentList = new ArrayList<Student>();
	private Boolean hasWriteAccess;

	public Entry(String name, Date startDate, Date enrolStartDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.enrolStartDate = enrolStartDate;

		this.hasWriteAccess = true;
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

	public void setHasWriteAccess(Boolean hasWriteAccess) {
		this.hasWriteAccess = hasWriteAccess;
	}

	public Boolean getHasWriteAccess() {
		return hasWriteAccess;
	}
}
