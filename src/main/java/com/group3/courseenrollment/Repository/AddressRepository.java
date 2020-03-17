package com.group3.courseenrollment.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group3.courseenrollment.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
