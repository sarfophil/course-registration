package com.group3.courseenrollment.Service;

import java.util.List;

import com.group3.courseenrollment.Repository.CourseRepository;
import com.group3.courseenrollment.domain.Course;

public class CourseServiceImpl implements CourseService {

	CourseRepository courseRepository;

	@Override
	public Course addCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> viewCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Course viewCourseById(Long course_id) {
		return courseRepository.findById(course_id).orElse(null);
	}

}
