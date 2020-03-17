package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
