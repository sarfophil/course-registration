package com.group3.courseenrollment.controller;

import java.net.URI;
import java.util.List;

import com.group3.courseenrollment.dto.StudentEnrollmentDto;
import com.group3.courseenrollment.service.StudentService;
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

import javax.validation.Valid;

@RestController
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;

	@Autowired
	private StudentService studentService;

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
	public ResponseEntity<Enrollment> addEnrollment(@RequestBody @Valid StudentEnrollmentDto studentEnrollmentDto) {
		studentService.addEnrollment(studentEnrollmentDto.getStudentId(),
				studentEnrollmentDto.getEnrollments(),studentEnrollmentDto.getSectionId());
		return ResponseEntity.created(URI.create("/enrollments/"+studentEnrollmentDto.getStudentId())).build();
	}
	 
	@GetMapping("/enrollments/{studentId}")
	public ResponseEntity<List<Enrollment>> getEnrollment(@PathVariable long studentId) {
		List<Enrollment> enrollments = studentService.loadEnrollmentByStudent(studentId);
		return ResponseEntity.ok(enrollments);
	}


	@PutMapping("/enrollments/{enrollmentId}")
	public ResponseEntity<Void> updateEnrollment(@PathVariable Long enrollmentId,@RequestBody Enrollment enrollment){
		enrollmentService.updateEnrollment(enrollmentId,enrollment);
		return ResponseEntity.ok().build();
	} 


}
