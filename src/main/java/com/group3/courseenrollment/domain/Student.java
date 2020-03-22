package com.group3.courseenrollment.domain;

import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long studentId;
	@NotBlank
	@Size(max=60)
	private String name;
	@Email
	private String email;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address mailingAddress;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Address homeAddress;
	
	@OneToMany(cascade = CascadeType.ALL)
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

}
