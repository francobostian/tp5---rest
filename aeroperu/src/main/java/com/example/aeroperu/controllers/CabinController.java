package com.example.aeroperu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esq.models.Cabin;
import com.example.aeroperu.pojo.CabinPojo;
import com.example.aeroperu.service.CabinService;

@RestController
@RequestMapping(value = "/cabin", produces = "Application/Json")
public class CabinController {

    @Autowired
    CabinService cabinService;

    /* to save a cabin */
    @PostMapping("/")
    public Cabin createCabin(@Valid @RequestBody Cabin cabin) {
	return cabinService.newObject(cabin);
    }

    /* get all cabins */
    @GetMapping("/")
    public List<CabinPojo> getAllCabins() {
	List<Cabin> cabins = (List<Cabin>) cabinService.getAll();

	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	List<CabinPojo> pojos = new ArrayList<CabinPojo>();

	for (Cabin c : cabins) {
	    pojos.add(new CabinPojo(c));
	}

	return pojos;
    }

    /* get Cabin by id */
    @GetMapping("/{id}")
    public ResponseEntity<CabinPojo> getCabinBy(@PathVariable(value = "id") Long airid) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Cabin cabin = cabinService.getById(airid);

	if (cabin == null) {
	    return ResponseEntity.notFound().build();
	}

	CabinPojo pojo = new CabinPojo(cabin);

	return new ResponseEntity<CabinPojo>(pojo, HttpStatus.OK);
    }

    /* Modify Cabin */
    @PutMapping("/{id}")
    public ResponseEntity<Cabin> updateCabin(@PathVariable(value = "id") Long airid,
	    @Valid @RequestBody Cabin airDetails) {

	Cabin cabin = cabinService.getById(airid);

	if (cabin == null) {
	    return ResponseEntity.notFound().build();
	}

	cabin.setName(airDetails.getName());

	Cabin updateCabin = cabinService.newObject(cabin);
	return ResponseEntity.ok().body(updateCabin);

    }

    /* Delete a Cabin */
    @DeleteMapping("/{id}")
    public ResponseEntity<Cabin> deleteCabin(@PathVariable(value = "id") Long airid) {

	Cabin air = cabinService.getById(airid);
	if (air == null) {
	    return ResponseEntity.notFound().build();
	}
	cabinService.removeObject(airid);

	return ResponseEntity.ok().build();

    }
}