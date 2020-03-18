package com.group3.courseenrollment.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Digits(integer = 6, fraction = 0)
	private long studentId;
	@NotBlank
	@Size(max=60)
	private String name;
	@Email
	private String email;

	@Valid
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address mailingAddress;
	@Valid
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address homeAddress;
	
	@Size(max=4)
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Enrollment> enrollmentList = new ArrayList<>();



	public Student(long studentId, String name, String email, Address mailingAddress, Address homeAddress) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.mailingAddress = mailingAddress;
		this.homeAddress = homeAddress;
	}

	public Student() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getStudent_id() {
		return studentId;
	}

	public void setStudent_id(long student_id) {
		this.studentId = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(Address mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public void setEnrollmentList(List<Enrollment> enrollmentList) {
		this.enrollmentList = enrollmentList;
	}

	public List<Enrollment> getEnrollmentList() {
		return enrollmentList;
	}
}
