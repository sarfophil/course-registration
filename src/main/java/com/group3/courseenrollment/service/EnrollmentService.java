package com.group3.courseenrollment.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.group3.courseenrollment.dto.EnrollmentDto;
import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;


public interface EnrollmentService {

	 public Enrollment addEnrollment(EnrollmentDto enrollmentDto) throws NoSuchElementException;
	 public Enrollment updateEnrollment(long enrollmentId,Long studentId, Enrollment enrollment)
			 throws NoSuchResourceException;

}
