package com.example.aeroperu.controllers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;
import com.example.aeroperu.controllers.AirportController;
import com.example.aeroperu.pojo.AirportPojo;
import com.example.aeroperu.service.AirportService;
import com.example.aeroperu.service.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest {

    @Mock
    private CityService cityService;
    @Mock
    private AirportService airportService;
    @InjectMocks
    protected AirportController controller;

    Country country = new Country("Argentina", "1234");
    State state = new State("Tucuman", "6525", country);
    City city = new City("San miguel", "MDP", state);
    Airport air = new Airport(1, "aeroparque", "MDP-8969", city, 5683, 7642);

    // CityPojo cityPojo = new CityPojo();
    AirportPojo airportPojo = new AirportPojo(air);

    List<Airport> puertos = new ArrayList<>();

    List<AirportPojo> pojos = new ArrayList<>();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	puertos.add(air);
	puertos.add(air);
	puertos.add(air);
	pojos.add(airportPojo);
	// air.setId(1);
    }

    @Test
    public void createAirportTest() {

	String[] code = this.air.getIataCode().split("-");

	Mockito.when(airportService.newObject(this.air)).thenReturn(this.air);
	Mockito.when(this.cityService.getByAttributeType(code[0])).thenReturn(this.city);
	City city = this.cityService.getByAttributeType(code[0]);

	assertEquals(city, this.city);
	assertEquals(city, this.air.getCity());
	Mockito.when(this.airportService.newObject(this.air)).thenReturn(this.air);
	Airport aero = this.airportService.newObject(this.air);
	assertEquals(aero, this.air);

	Mockito.when(this.controller.createAirport(aero)).thenReturn(this.air);

    }

    @Test
    public void getAllAirportTest() {
	// Mockito.when(this.controller.getAllAirports()).thenReturn(this.pojos);
    }

    @Test
    public void getByOneAirportTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Airport airpor = null;
	assertNull(airpor);
	long id = this.air.getId();

	String iata = this.air.getIataCode();
	Mockito.when(this.airportService.getByAttributeType(iata)).thenReturn(this.air);
	airpor = this.airportService.getByAttributeType(iata);

	assertEquals(this.air, airpor);
	assertNotNull(this.air);
	status = this.controller.getAirportById(iata);
	assertEquals(new ResponseEntity(this.air, HttpStatus.OK), status);

    }

    @Test
    public void putAirportTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	assertNotNull(this.air);
	Mockito.when(this.airportService.getById(this.air.getId())).thenReturn(this.air);
	Airport airportdb = this.airportService.getById(this.air.getId());
	assertNotNull(airportdb);
	String iata = this.air.getIataCode();
	String[] code = iata.split("-");

	Mockito.when(this.cityService.getByAttributeType(code[0])).thenReturn(this.city);
	City ct = this.cityService.getByAttributeType(code[0]);

	assertEquals(ct, this.air.getCity());
	assertEquals(this.air.getIataCode(), airportdb.getIataCode());

	Mockito.when(this.airportService.newObject(this.air)).thenReturn(this.air);
	Airport airt = this.airportService.newObject(this.air);
	assertEquals(this.air, airt);

	status = this.controller.updateAirport(air.getId(), this.air);

    }

    @Test
    public void deleteAirportTestNull() {
	ResponseEntity ent = new ResponseEntity(HttpStatus.OK);
	assertNotNull(air.getId());

	ResponseEntity status = this.controller.deleteAirport(air.getId());
	assertEquals(ent, status);

    }

}
