package com.group3.courseenrollment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Section;
import com.group3.courseenrollment.exception.NoSuchResourceException;

public interface SectionService {
	public List<Section> getAllSections();
	
	public Section addSection(Section section);
	
	public Section getSection(Long sectionId) throws NoSuchResourceException;
	
	public Section updateSection(Long sectionId, Section new_Section) throws NoSuchResourceException;
	
	public ResponseEntity<Void> deleteSection(Long sectionId) throws NoSuchResourceException;

}
