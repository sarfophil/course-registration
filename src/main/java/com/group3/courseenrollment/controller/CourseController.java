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
import com.group3.courseenrollment.exception.NoSuchResourceException;

import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses() {
		HttpHeaders headers = new HttpHeaders();
		List<Course> courses = courseService.getAllCourses();

		if (courses == null) {
			return new ResponseEntity<List<Course>>(HttpStatus.NOT_FOUND);
		}

		headers.add("Number Of Records Found", String.valueOf(courses.size()));
		return new ResponseEntity<List<Course>>(courses, headers, HttpStatus.OK);

	}

	@PostMapping("/courses")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {
	
		if (course == null) {
			return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
		}
		
		courseService.addCourse(course);
		
		//return ResponseEntity.created(URI.create("/courses/" + course.getCode())).build();
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@GetMapping("/courses/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable long courseId) {
		Course course = courseService.getCourse(courseId);
		if (course == null) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@PutMapping("/courses/{courseId}")
	public ResponseEntity<Course> update(@PathVariable long courseId, @RequestBody Course course) throws NoSuchResourceException  {
		HttpHeaders headers = new HttpHeaders();
		Course isExist = courseService.getCourse(courseId);
		if (isExist == null) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		courseService.updateCourse(courseId, course);
		headers.add("Course Updated  - ", String.valueOf(courseId));
		return new ResponseEntity<Course>(course, headers, HttpStatus.OK);
	}

	@DeleteMapping("/courses/delete/{courseId}")
	public ResponseEntity<Void> deleteCourse(@PathVariable long courseId) throws NoSuchResourceException  {
		courseService.deleteCourse(courseId);
		return ResponseEntity.noContent().build();

	}
}
