package com.group3.courseenrollment.Service;

import java.util.List;

import com.group3.courseenrollment.domain.Offering;

public interface OfferingService {

	public List<Offering> getAllOfferings();

	public Offering getOfferingById(Long offeringId);

}
