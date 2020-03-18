package com.group3.courseenrollment.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import com.group3.courseenrollment.dto.StudentEnrollmentDto;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.service.EnrollmentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/enrollments/")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;


	@PutMapping("/{enrollmentId}/{studentId}")
	public @ResponseBody Enrollment updateEnrollment(@PathVariable Long enrollmentId,
					 @PathVariable Long studentId,
					 @RequestBody Enrollment enrollment){

		return enrollmentService.updateEnrollment(enrollmentId,studentId,enrollment);
	}


	 

}
