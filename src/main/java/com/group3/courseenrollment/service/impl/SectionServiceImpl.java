package com.group3.courseenrollment.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.group3.courseenrollment.domain.Enrollment;
import com.group3.courseenrollment.domain.Faculty;
import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.dto.SectionDto;
import com.group3.courseenrollment.repository.EnrollmentRepository;
import com.group3.courseenrollment.repository.FacultyRepository;
import com.group3.courseenrollment.repository.OfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.SectionRepository;
import com.group3.courseenrollment.service.SectionService;


@Service
public class SectionServiceImpl implements SectionService{
	
	@Autowired
    private SectionRepository sectionRepository;

	@Autowired
    private EnrollmentRepository enrollmentRepository;


	@Autowired
    private FacultyRepository facultyRepository;

	@Autowired
    private OfferingRepository offeringRepository;

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Section> getAllSections(){
        return sectionRepository.findAll();
    }

    @Secured("ROLE_ADMIN")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Section addSection(SectionDto sectionDto) throws NoSuchElementException{
      List<Faculty> faculties = sectionDto.getFacultyList()
              .stream()
              .filter(facultyId->{
                    return facultyRepository.findById(facultyId).isPresent();
              })
              .map(facultyId->{
                  return facultyRepository.findById(facultyId).get();
              }).collect(Collectors.toList());

      Optional<Offering> offeringOptional = offeringRepository.findById(sectionDto.getOfferingId());
      offeringOptional.orElseThrow(NoSuchElementException::new);

      Section section = new Section();
      section.setFaculty(faculties);
      section.setOffering(offeringOptional.get());

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
