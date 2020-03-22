package com.group3.courseenrollment.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;


@Data
@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Section section;

	@FutureOrPresent
	private LocalDate enrolStartDate;

	@Future
	private LocalDate enrolEndDate;

	public Enrollment(Section section,LocalDate enrolStartDate, LocalDate enrolEndDate) {
		super();
		this.enrolStartDate = enrolStartDate;
		this.enrolEndDate = enrolEndDate;
		this.section = section;
	}

	public Enrollment() {

	}

}




