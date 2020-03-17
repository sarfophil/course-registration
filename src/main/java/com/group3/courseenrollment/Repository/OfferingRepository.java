package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Offering;

public interface OfferingRepository extends JpaRepository<Offering, Long> {

}
