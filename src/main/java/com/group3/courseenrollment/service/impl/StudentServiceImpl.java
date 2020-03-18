package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.configuration.ApplicationProperties;
import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.domain.Entry;
import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.domain.Student;
import com.group3.courseenrollment.exception.EnrollmentLimitExceededException;
import com.group3.courseenrollment.repository.EntryRepository;
import com.group3.courseenrollment.repository.SectionRepository;
import com.group3.courseenrollment.repository.StudentRepository;
import com.group3.courseenrollment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private ApplicationProperties applicationProperties;


    @Secured("ROLE_STUDENT")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addEnrollment(Long studentId,List<Enrollment> enrollments,Long sectionId)
            throws EnrollmentLimitExceededException,NoSuchElementException{


        if(enrollments.size() > applicationProperties.getEnrollmentLimitPerStudent()) {
            throw new EnrollmentLimitExceededException("Student Enrollment Exceed");
        }else if (enrollments.size() < applicationProperties.getEnrollmentLimitPerStudent()){
            throw new EnrollmentLimitExceededException("Student Enrollment should choose "
                    +applicationProperties.getEnrollmentLimitPerStudent());
        }

        // Lookup for student
        Optional<Student> optionalStudent = studentRepository.findByStudentId(studentId);
        optionalStudent.orElseThrow(()-> new NoSuchElementException("Student not found"));

        // Look for Section
        Optional<Section> optionalSection = sectionRepository.findById(studentId);
        optionalSection.orElseThrow(()-> new NoSuchElementException("Section not found"));





        Student student = optionalStudent.get();
        Section section = optionalSection.get();

        if(validateEnrollmentPeriodAndEnrollments(student,enrollments)){

            student.setEnrollmentList(enrollments);
            section.setEnrollment(enrollments);

            studentRepository.save(student);
            sectionRepository.save(section);
        }


    }

    @Override
    public List<Enrollment> loadEnrollmentByStudent(Long student) throws NoSuchElementException{
        // Lookup for student
        Optional<Student> optionalStudent = studentRepository.findByStudentId(student);
        optionalStudent.orElseThrow(()-> new NoSuchElementException("Student not found"));

        return optionalStudent.get().getEnrollmentList();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    private Boolean validateEnrollmentPeriodAndEnrollments(Student student,List<Enrollment> enrollments){
        Optional<Entry> entryOptional = entryRepository.findByStudentListStudentId(student.getStudent_id());
        if(entryOptional.isPresent()){
            // Compare Period
            Entry entry = entryOptional.get();
            for (Enrollment enrollment : enrollments){
                if(enrollment.getEnrolStartDate().compareTo(entry.getEnrolStartDate()) > 0
                        && enrollment.getEnrolEndDate().compareTo(entry.getEnrolStartDate()) < 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}
