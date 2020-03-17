package com.group3.courseenrollment.service;


import org.springframework.http.ResponseEntity;
import com.group3.courseenrollment.NoSuchResourceException;
import com.group3.courseenrollment.domain.Course;
import java.util.List;

public interface CourseService {
	
	public List<Course> getAllCourses();
	
	public Course addCourse(Course course);
	
	public Course getCourse(long courseId) throws NoSuchResourceException;
	
	public Course updateCourse(long courseId, Course new_Course) throws NoSuchResourceException;
	
	public ResponseEntity<Void> deleteCourse(long courseId) throws NoSuchResourceException;
    
}

