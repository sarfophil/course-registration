package com.group3.courseenrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
