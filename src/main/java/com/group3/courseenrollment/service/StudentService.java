package com.group3.courseenrollment.service;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.domain.Student;
import com.group3.courseenrollment.dto.StudentEnrollmentDto;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.exception.HasNoWriteException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface StudentService {
    /**
     * student should be able to create up to 4 enrollments for the future 4
     * blocks during the enrollment period.
     * @param studentEnrollmentDto
     * @throws EnrollmentLimitExceededException
     */
    public void addEnrollment(Long studentId, StudentEnrollmentDto studentEnrollmentDto)
            throws EnrollmentLimitExceededException,NoSuchElementException, HasNoWriteException;

    public List<Enrollment> loadEnrollmentByStudent(Long student);

    public Optional<Enrollment> loadStudentEnrollmentByEnrollmentId(Long enrollment,Long studentId);

    public void addStudent(Student student);



}
