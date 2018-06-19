package com.example.aeroperu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esq.models.Cabin;
import com.example.aeroperu.repo.CabinRepo;

@Service
public class CabinService {

    @Autowired
    private CabinRepo cabin;

    public List<Cabin> getAll() {
	return this.cabin.findAll();
    }

    public Cabin getByAttributeType(String value) {
	return this.cabin.getAttribute(value);
    }

    public Cabin getById(Long id) {
	Cabin cabin = null;
	Optional<Cabin> cabinOptional = this.cabin.findById(id);
	if (cabinOptional.isPresent()) {
	    cabin = cabinOptional.get();
	}
	return cabin;
    }

    public Cabin newObject(Cabin value) {
	if (value != null) {
	    this.cabin.save(value);
	}
	return value;
    }

    public void removeObject(Long id) {
	this.cabin.deleteById(id);
    }
}
