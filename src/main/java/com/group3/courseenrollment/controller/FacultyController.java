package com.group3.courseenrollment.controller;

import com.group3.courseenrollment.domain.Faculty;
import com.group3.courseenrollment.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("faculties")
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @PostMapping
    public ResponseEntity<?> createFaculty(@RequestBody @Valid Faculty faculty){
        facultyRepository.save(faculty);
        return ResponseEntity.created(URI.create("/faculties")).build();
    }
}
