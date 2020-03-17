package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}
