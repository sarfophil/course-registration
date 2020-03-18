package com.group3.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.service.EnrollmentService;

@RestController
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;
	
	@GetMapping("/enrollments")
	public ResponseEntity<List<Enrollment>> getAllEnrollments() {
		HttpHeaders headers = new HttpHeaders();
		List<Enrollment> enrollments = enrollmentService.getAllEnrollments();
		
		if (enrollments == null) {
			return new ResponseEntity<List<Enrollment>>(HttpStatus.NOT_FOUND);
		}
		
		headers.add("Number Of Records Found", String.valueOf(enrollments.size()));
		return new ResponseEntity<List<Enrollment>>(enrollments, headers, HttpStatus.OK);
	}
	 
	@PostMapping("/enrollments")
	public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) throws NoSuchResourceException {
		HttpHeaders headers = new HttpHeaders();
		
		if (enrollment == null) {
			return new ResponseEntity<Enrollment>(HttpStatus.BAD_REQUEST);
		}
		
		enrollmentService.addEnrollment(enrollment);
		headers.add("Enrollment Created  - ", String.valueOf(enrollment.getId()));
		return new ResponseEntity<Enrollment>(enrollment, headers, HttpStatus.CREATED);
	}
	 
	@GetMapping("/enrollments/{enrollmentId}")
	public ResponseEntity<Enrollment> getEnrollment(@PathVariable long enrollmentId) {
		Enrollment enrollment = enrollmentService.getEnrollment(enrollmentId);
		if (enrollment == null) {
			return new ResponseEntity<Enrollment>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Enrollment>(enrollment, HttpStatus.OK);
	}
	 
	@PutMapping("/enrollments/{enrollmentId}")
	public ResponseEntity<Enrollment> updateEnrollment(@PathVariable long enrollmentId, @RequestBody Enrollment new_Enrollment) throws NoSuchResourceException {
		HttpHeaders headers = new HttpHeaders();
		Enrollment isExist = enrollmentService.getEnrollment(enrollmentId);
		if (isExist == null) {
			return new ResponseEntity<Enrollment>(HttpStatus.NOT_FOUND);
		} 
		enrollmentService.updateEnrollment(enrollmentId, new_Enrollment);
		headers.add("Enrollment Updated  - ", String.valueOf(enrollmentId));
		return new ResponseEntity<Enrollment>(new_Enrollment, headers, HttpStatus.OK);
	} 
	
	@DeleteMapping("/enrollments/delete/{enrollmentId}")
	public ResponseEntity<Void> deleteEnrollment(@PathVariable long enrollmentId) throws NoSuchResourceException {
		enrollmentService.deleteEnrollment(enrollmentId);
		return  ResponseEntity.noContent().build(); 
	}
}
