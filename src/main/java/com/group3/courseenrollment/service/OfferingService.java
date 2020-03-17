package com.group3.courseenrollment.service;

import org.springframework.http.ResponseEntity;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.exception.NoSuchResourceException;

import java.util.List;

public interface OfferingService {
	public List<Offering> getAllOfferings();
	public Offering addOffering(Offering offering);
	public Offering getOffering(long offeringId) throws NoSuchResourceException;
	public Offering updateOffering(long offeringId, Offering new_Offering) throws NoSuchResourceException;
	public ResponseEntity<Void> deleteOffering(long offeringId) throws NoSuchResourceException;
}