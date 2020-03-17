package com.group3.courseenrollment.domain;

public class Student {
	private long id;
	private long student_id;
	private String name;
	private String email;
	private Address mailingAddress;
	private Address homeAddress;

	public Student(long student_id, String name, String email, Address mailingAddress, Address homeAddress) {
		super();
		this.student_id = student_id;
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
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
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

}
