package com.group3.courseenrollment.Service;

import java.util.List;

import com.ezat.Models.Offering;
import com.ezat.Repositories.OfferingRepository;

public class OffringServiceImpl implements OfferingService {

	OfferingRepository offeringRepository;

	@Override
	public List<Offering> getAllOfferings() {
		return offeringRepository.findAll();

	}

	@Override
	public Offering getOfferingById(Long offeringId) {
		return offeringRepository.findById(offeringId).orElse(null);

	}

}
