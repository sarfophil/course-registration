package com.group3.courseenrollment.controller;

import com.group3.courseenrollment.dto.EnrollmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.service.EnrollmentService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	@Autowired
	private EnrollmentService enrollmentService;


	@PostMapping
	public ResponseEntity<Enrollment> addEnrollment(@RequestBody EnrollmentDto enrollmentDto){
		Enrollment enrollment = enrollmentService.addEnrollment(enrollmentDto);
		return ResponseEntity.created(URI.create("/"+String.valueOf(enrollment.getId()))).body(enrollment);
	}

	@PutMapping("/{enrollmentId}/student/{studentId}")
	public @ResponseBody Enrollment updateEnrollment(@PathVariable Long enrollmentId,
					 @PathVariable Long studentId,
					 @RequestBody @Valid Enrollment enrollment){

		return enrollmentService.updateEnrollment(enrollmentId,studentId,enrollment);
	}


	 

}
