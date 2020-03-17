package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Entry;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
