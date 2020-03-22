package com.group3.courseenrollment.repository;


import com.group3.courseenrollment.domain.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section,Long> {

}
