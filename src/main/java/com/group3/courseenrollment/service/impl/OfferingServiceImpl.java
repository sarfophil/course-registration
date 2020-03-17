package com.group3.courseenrollment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.exception.NoSuchResourceException;
import com.group3.courseenrollment.repository.OfferingRepository;
import com.group3.courseenrollment.service.OfferingService;


@Service
public class OfferingServiceImpl implements OfferingService{
	
	 	@Autowired
	    private OfferingRepository offeringRepository;

	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public List<Offering> getAllOfferings(){
	        return offeringRepository.findAll();
	    }

	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering addOffering(Offering offering){
	        return offeringRepository.save(offering);
	    }

	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering getOffering(long offeringId) throws NoSuchResourceException {
	        Offering offering = offeringRepository.findById(offeringId)
	                .orElseThrow(() -> new NoSuchResourceException("Can't find offering", offeringId));
	        return offering;
	    }

	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public Offering updateOffering(long offeringId, Offering new_Offering) throws NoSuchResourceException{
	        Offering offering = offeringRepository.findById(offeringId)
	                .orElseThrow(() -> new NoSuchResourceException("Can't find offering", offeringId));
	        offering.setCode(new_Offering.getCode());

	        final Offering updated_Offering = offeringRepository.save(offering);

	        return updated_Offering;
	    }

	    // ResponseEntity Should be at the controller therefore you can change it later to void
	    @Transactional(propagation = Propagation.REQUIRES_NEW)
	    public ResponseEntity<Void> deleteOffering(long offeringId) throws NoSuchResourceException{
	        Offering offering = offeringRepository.findById(offeringId)
	                .orElseThrow(() -> new NoSuchResourceException("Cant find offering", offeringId));
	        offeringRepository.delete(offering);
	        return  ResponseEntity.noContent().build();
	    }

}
