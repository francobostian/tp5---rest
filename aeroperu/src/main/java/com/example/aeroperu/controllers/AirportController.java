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

import com.esq.models.Airport;
import com.esq.models.City;
import com.example.aeroperu.pojo.AirportPojo;
import com.example.aeroperu.service.AirportService;
import com.example.aeroperu.service.CityService;

@RestController
@RequestMapping(value = "/airport", produces = "Application/Json")
public class AirportController {
    @Autowired
    AirportService airportService;

    @Autowired
    CityService cityService;

    /* to save an port */
    @PostMapping("/")
    public Airport createAirport(@Valid @RequestBody Airport airport) {

	String iataCode = airport.getIataCode();
	String[] code = iataCode.split("-");

	City city = this.cityService.getByAttributeType(code[0]);

	airport.setCity(city);
	airport.setIataCode(code[1]);
	return airportService.newObject(airport);
    }

    /* get all airports */
    @GetMapping("/")
    public ResponseEntity<List<AirportPojo>> getAllAirports() {
	List<Airport> airportList = (List<Airport>) airportService.findAll();

	List<AirportPojo> pojos = new ArrayList<AirportPojo>();

	for (Airport a : airportList) {
	    pojos.add(new AirportPojo(a));
	}

	return new ResponseEntity<List<AirportPojo>>(pojos, HttpStatus.OK);
    }

    /* get airport by airid */
    @GetMapping("/{id}")
    public ResponseEntity<AirportPojo> getAirportById(@PathVariable(value = "id") String iata) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Airport airport = airportService.getByAttributeType(iata);
	AirportPojo pojo = new AirportPojo(airport);
	if (airport == null) {
	    return ResponseEntity.notFound().build();
	}
	System.err.println(airport.getId());

	status = new ResponseEntity<AirportPojo>(pojo, HttpStatus.OK);
	return status;

    }

    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable(value = "id") Long id,
	    @Valid @RequestBody Airport airDetails) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Airport air = airportService.getById(id);

	if (air == null) {
	    return ResponseEntity.notFound().build();
	}

	String iataCode = airDetails.getIataCode();
	System.out.println(iataCode);
	String[] code = iataCode.split("-");
	System.out.println(code[0]);
	System.out.println(code[1]);

	City city = this.cityService.getByAttributeType(code[0]);

	Airport airport = new Airport();
	airport.setId(id);
	airport.setIataCode(code[1]);
	airport.setName(airDetails.getName());
	airport.setLatitude(airDetails.getLatitude());
	airport.setLongitude(airDetails.getLongitude());
	airport.setCity(city);

	Airport updateAirport = airportService.newObject(airport);
	status = new ResponseEntity<Airport>(updateAirport, HttpStatus.OK);
	return status;

    }

    /* Delete an airport */
    @DeleteMapping("/{id}")
    public ResponseEntity<Airport> deleteAirport(@PathVariable(value = "id") Long id) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Airport air = airportService.getById(id);
	if (air == null) {
	    return ResponseEntity.notFound().build();
	}
	airportService.removeObject(id);
	status = new ResponseEntity(HttpStatus.OK);
	return status;

    }

    ////////////////////////////////////////////////////////////////////////////////////////

}