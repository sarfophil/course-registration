package com.group3.courseenrollment.service;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;

import java.util.List;
import java.util.NoSuchElementException;

public interface StudentService {
    /**
     * student should be able to create up to 4 enrollments for the future 4
     * blocks during the enrollment period.
     * @param enrollments
     * @throws EnrollmentLimitExceededException
     */
    public void addEnrollment(Long studentId,List<Enrollment> enrollments,Long sectionId) throws EnrollmentLimitExceededException;


}
