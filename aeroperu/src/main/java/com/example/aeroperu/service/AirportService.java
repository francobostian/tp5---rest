package com.example.aeroperu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esq.models.Airport;
import com.example.aeroperu.repo.AirportRepo;

@Service
public class AirportService {

    @Autowired
    private AirportRepo airportRepository;

    public void removeObject(Long id) {
	this.airportRepository.deleteById(id);
    }

    public Airport getById(Long id) {
	Airport airport = null;
	Optional<Airport> airportaOptional = this.airportRepository.findById(id);
	if (airportaOptional.isPresent()) {
	    airport = airportaOptional.get();
	}
	return airport;
    }

    public List<Airport> findAll() {
	return this.airportRepository.findAll();
    }

    public Airport getByAttributeType(String iata) {
	return this.airportRepository.getAttribute(iata);
    }

    public Airport newObject(Airport airport) {
	if (airport != null) {
	    this.airportRepository.save(airport);
	}

	return airport;
    }
}
