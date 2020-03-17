package com.group3.courseenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.service.OfferingService;


@RestController
public class OfferingController {
	
	@Autowired
	private OfferingService offeringService;
	
	@RequestMapping(value="/offerings", method=RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Offering>> getAllOfferings() {
		HttpHeaders headers = new HttpHeaders();
		List<Offering> offerings = offeringService.getAllOfferings();
		if (offerings == null) {
			return new ResponseEntity<List<Offering>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(offerings.size()));
		return new ResponseEntity<List<Offering>>(offerings, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/offerings/{offeringID}", method=RequestMethod.GET)
	public ResponseEntity<Offering> getOffering(@PathVariable long offeringID) {
		Offering offering = offeringService.getOffering(offeringID);
		if (offering == null) {
			return new ResponseEntity<Offering>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Offering>(offering, HttpStatus.OK);
	}
		
	@RequestMapping(value="/offerings", method=RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Offering> addOffering(@RequestBody Offering offering){
		HttpHeaders headers = new HttpHeaders();
		if (offering == null) {
			return new ResponseEntity<Offering>(HttpStatus.BAD_REQUEST);
		}
		offeringService.addOffering(offering);
		headers.add("Offering Created  - ", String.valueOf(offering.getId()));
		return new ResponseEntity<Offering>(offering, headers, HttpStatus.CREATED);
		 
    }	
	
	
	@RequestMapping(value="/offerings/{offeringId}", method=RequestMethod.PUT)
	public ResponseEntity<Offering> updateOffering(@PathVariable long offeringId,@RequestBody Offering offering) {
		HttpHeaders headers = new HttpHeaders();
		Offering isExist = offeringService.getOffering(offeringId);
		if (isExist == null) {
			return new ResponseEntity<Offering>(HttpStatus.NOT_FOUND);
		} 
		offeringService.updateOffering(offeringId, offering);
		headers.add("Offering Updated  - ", String.valueOf(offeringId));
		return new ResponseEntity<Offering>(offering, headers, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/offerings/delete/{offeringId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOffering(@PathVariable long offeringId){
		offeringService.deleteOffering(offeringId);
		return  ResponseEntity.noContent().build(); 
	}
	
}
