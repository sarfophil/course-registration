package com.group3.courseenrollment.Service;

import java.util.List;

import com.group3.courseenrollment.domain.Course;

public interface CourseService {

	public Course addCourse(Course course);

	public List<Course> viewCourses();

	public Course viewCourseById(Long course_id);

}
