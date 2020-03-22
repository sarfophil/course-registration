package com.group3.courseenrollment.service.impl;

import com.group3.courseenrollment.domain.Faculty;
import com.group3.courseenrollment.repository.FacultyRepository;
import com.group3.courseenrollment.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;

public class FacultyServiceImpl implements FacultyService {
    @Autowired private FacultyRepository facultyRepository;


    @Override
    public void addFaculty(Faculty faculty) {
        facultyRepository.save(faculty);
    }
}
