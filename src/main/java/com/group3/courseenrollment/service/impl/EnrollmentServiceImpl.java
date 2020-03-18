package com.group3.courseenrollment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.EnrollmentRepository;
import com.group3.courseenrollment.service.EnrollmentService;

@Secured({"ROLE_FACULTY","ROLE_ADMIN","ROLE_STUDENT"})
@Service
public class EnrollmentServiceImpl implements EnrollmentService{
	
	@Autowired
    private EnrollmentRepository enrollmentRepository;

    @Transactional(propagation =Propagation.REQUIRES_NEW)
    public List<Enrollment> getAllEnrollments(){
        return enrollmentRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Enrollment addEnrollment(Enrollment enrollment){
        return enrollmentRepository.save(enrollment);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Enrollment getEnrollment(long enrollmentId) throws NoSuchResourceException{
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find Enrollment", enrollmentId));
        return enrollment;
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Enrollment updateEnrollment(long enrollmentId, Enrollment new_Enrollment) throws NoSuchResourceException{
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find enrollment", enrollmentId));

        return enrollmentRepository.save(enrollment);
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> deleteEnrollment(long enrollmentId) throws NoSuchResourceException{
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NoSuchResourceException("Cant find enrollment", enrollmentId));
        enrollmentRepository.delete(enrollment);
        return  ResponseEntity.noContent().build();
}

}
