package com.example.aeroperu.services.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;
import com.example.aeroperu.repo.AirportRepo;
import com.example.aeroperu.service.AirportService;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportServiceTest {

    @Mock
    private AirportRepo airportRepository;

    @InjectMocks
    private AirportService service;

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BUE", st);
    Airport airport = new Airport(1, "San Martin", "ARG", city, 333, 444);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getALlTest() throws Exception {
	List<Airport> listAirport = new ArrayList<Airport>();
	listAirport.add(this.airport);
	listAirport.add(this.airport);
	listAirport.add(this.airport);
	Mockito.when(this.airportRepository.findAll()).thenReturn(listAirport);
	List<Airport> dao = this.service.findAll();
	assertEquals(3, listAirport.size());
    }

    @Test
    public void addTest() throws Exception {
	Mockito.when(this.airportRepository.save(this.airport)).thenReturn(this.airport);
	Airport air = this.service.newObject(this.airport);
	assertEquals(1, air.getId());
	assertEquals("San Martin", air.getName());
	assertEquals("ARG", air.getIataCode());
	assertEquals(city, air.getCity());
	assertEquals(333, air.getLatitude(), 0);
	assertEquals(444, air.getLongitude(), 0);
    }

    @Test
    public void removeTest() throws Exception {
	service.removeObject(this.airport.getId());
	Mockito.verify(this.airportRepository, Mockito.times(1)).deleteById(this.airport.getId());
    }

    @Test
    public void getByIdTest() throws Exception {
	Mockito.when(this.airportRepository.findById(this.airport.getId()))
		.thenReturn(java.util.Optional.ofNullable(this.airport));
	Airport air = this.service.getById(this.airport.getId());
	assertEquals(1, air.getId());
	assertEquals("San Martin", air.getName());
	assertEquals("ARG", air.getIataCode());
	assertEquals(city, air.getCity());
	assertEquals(333, air.getLatitude(), 0);
	assertEquals(444, air.getLongitude(), 0);
    }

    @Test
    public void getByAttributeTypeTest() throws Exception {
	Mockito.when(this.airportRepository.getAttribute(this.airport.getIataCode())).thenReturn(this.airport);
	Airport air = this.service.getByAttributeType(this.airport.getIataCode());
	assertEquals(1, air.getId());
	assertEquals("San Martin", air.getName());
	assertEquals("ARG", air.getIataCode());
	assertEquals(city, air.getCity());
	assertEquals(333, air.getLatitude(), 0);
	assertEquals(444, air.getLongitude(), 0);
    }

}
