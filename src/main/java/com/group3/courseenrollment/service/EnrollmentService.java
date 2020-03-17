package com.group3.courseenrollment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;

public interface EnrollmentService {
	
	 public List<Enrollment> getAllEnrollments();
	 
	 public Enrollment addEnrollment(Enrollment enrollment);
	 
	 public Enrollment getEnrollment(long enrollmentId) throws NoSuchResourceException;
	 
	 public Enrollment updateEnrollment(long enrollmentId, Enrollment new_Enrollment) throws NoSuchResourceException; 
	 
	 public ResponseEntity<Void> deleteEnrollment(long enrollmentId) throws NoSuchResourceException;
	
}
