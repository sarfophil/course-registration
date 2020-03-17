package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
