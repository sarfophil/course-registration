package com.group3.courseenrollment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.SectionRepository;
import com.group3.courseenrollment.service.SectionService;

public class SectionServiceImpl implements SectionService{
	
	@Autowired
    private SectionRepository sectionRepository;

    @Secured({"ROLE_STUDENT","ROLE_ADMIN"})
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Section addSection(Section section){
        return sectionRepository.save(section);
    }


    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Section getSection(Long sectionId) throws NoSuchResourceException{
        return sectionRepository.findById(sectionId).
                orElseThrow(() -> new NoSuchResourceException("Can't find Section", 1L));
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Section updateSection(Long sectionId, Section new_Section) throws NoSuchResourceException{
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NoSuchResourceException("Can't find section", 1L));
        section.setFaculty(new_Section.getFaculty());
        //section.setEnrollment(new_Section.getEnrollmentList());

        return sectionRepository.save(section);
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Void> deleteSection(Long sectionId) throws NoSuchResourceException{
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new NoSuchResourceException("Cant find section", 1L));
        sectionRepository.delete(section);
        return  ResponseEntity.noContent().build();
    }

}
