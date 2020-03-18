package com.group3.courseenrollment.controller;


import java.util.List;
import com.group3.courseenrollment.exception.NoSuchResourceException;

import com.group3.courseenrollment.domain.Offering;
import com.group3.courseenrollment.service.OfferingService;


import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
public class OfferingController {
	
	@Autowired
	private OfferingService offeringService;

	@GetMapping("/offerings")
	public ResponseEntity<List<Offering>> getAllOfferings() {
		HttpHeaders headers = new HttpHeaders();
		List<Offering> offerings = offeringService.getAllOfferings();
		if (offerings == null) {
			return new ResponseEntity<List<Offering>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(offerings.size()));
		return new ResponseEntity<List<Offering>>(offerings, headers, HttpStatus.OK);
	}
	
	
	@GetMapping("/offerings/{offeringId}")
	public ResponseEntity<Offering> getOffering(@PathVariable long offeringId) {
		Offering offering = offeringService.getOffering(offeringId);
		if (offering == null) {
			return new ResponseEntity<Offering>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Offering>(offering, HttpStatus.OK);
	}
		
	
	@PostMapping("offerings")
	public ResponseEntity<Offering> addOffering(@RequestBody Offering offering){
		HttpHeaders headers = new HttpHeaders();
		if (offering == null) {
			return new ResponseEntity<Offering>(HttpStatus.BAD_REQUEST);
		}
		offeringService.addOffering(offering);
		headers.add("Offering Created  - ", String.valueOf(offering.getId()));
		return new ResponseEntity<Offering>(offering, headers, HttpStatus.CREATED);
		 
    }	
	
	
	@PutMapping("/offerings/{offeringId}")
	public ResponseEntity<Offering> updateOffering(@PathVariable long offeringId,@RequestBody Offering offering) throws NoSuchResourceException{
		HttpHeaders headers = new HttpHeaders();
		Offering isExist = offeringService.getOffering(offeringId);
		if (isExist == null) {
			return new ResponseEntity<Offering>(HttpStatus.NOT_FOUND);
		} 
		offeringService.updateOffering(offeringId, offering);
		headers.add("Offering Updated  - ", String.valueOf(offeringId));
		return new ResponseEntity<Offering>(offering, headers, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/offerings/delete/{offeringId}")
	public ResponseEntity<Void> deleteOffering(@PathVariable long offeringId) throws NoSuchResourceException {
		offeringService.deleteOffering(offeringId);
		return  ResponseEntity.noContent().build(); 
	}


	
}
