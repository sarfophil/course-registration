package com.group3.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.group3.courseenrollment.domain.Course;
import com.group3.courseenrollment.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/courses", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Course>> getAllCourses() {
		HttpHeaders headers = new HttpHeaders();
		List<Course> courses = courseService.getAllCourses();
		if (courses == null) {
			return new ResponseEntity<List<Course>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(courses.size()));
		return new ResponseEntity<List<Course>>(courses, headers, HttpStatus.OK);

	}

	@RequestMapping(value = "/courses", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Course> addCourse(@RequestBody Course course) {

		HttpHeaders headers = new HttpHeaders();
		if (course == null) {
			return new ResponseEntity<Course>(HttpStatus.BAD_REQUEST);
		}
		courseService.addCourse(course);
		headers.add("Course Created  - ", String.valueOf(course.getId()));
		return new ResponseEntity<Course>(course, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/courses/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<Course> getCourse(@PathVariable long courseId) {
		Course course = courseService.getCourse(courseId);
		if (course == null) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/{courseId}", method = RequestMethod.PUT)
	public ResponseEntity<Course> update(@PathVariable long courseId, @RequestBody Course course) {
		HttpHeaders headers = new HttpHeaders();
		Course isExist = courseService.getCourse(courseId);
		if (isExist == null) {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		} 
		courseService.updateCourse(courseId, isExist);
		headers.add("Course Updated  - ", String.valueOf(courseId));
		return new ResponseEntity<Course>(course, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/courses/delete/{courseId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourse(@PathVariable long courseId) {
		courseService.deleteCourse(courseId);
		return  ResponseEntity.noContent().build(); 
		
	}
}
