package com.group3.courseenrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group3.courseenrollment.domain.Enrollment;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
}
