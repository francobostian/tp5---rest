package com.example.aeroperu.services.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.esq.models.Route;
import com.esq.models.State;
import com.example.aeroperu.repo.RouteRepo;
import com.example.aeroperu.service.RouteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RouteServiceTest {

    @Mock
    private RouteRepo routeRepository;

    @InjectMocks
    private RouteService service;

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BS", st);
    City city2 = new City(1, "Rosario", "R", st);
    Airport airportBegin = new Airport(1, "AreolineasArgentinas", "ARG", city, -222, 222);
    Airport airportEnd = new Airport(2, "MDP", "MDP", city2, -222, 222);
    Route rt = new Route(1, airportBegin, airportEnd, 100);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getALlTest() throws Exception {
	List<Route> listRoutes = new ArrayList<Route>();
	listRoutes.add(this.rt);
	listRoutes.add(this.rt);
	listRoutes.add(this.rt);
	when(this.routeRepository.findAll()).thenReturn(listRoutes);
	List<Route> dao = this.service.getAll();
	assertEquals(3, listRoutes.size());
    }

    @Test
    public void addTest() throws Exception {
	when(this.routeRepository.save(this.rt)).thenReturn(this.rt);
	Route rte = this.service.newSave(this.rt);
	assertEquals(this.airportEnd, rte.getAirportEnd());
	assertEquals(this.airportBegin, rte.getAirportBegin());

	assertEquals(100, rte.getDistance());
	assertEquals(1, rte.getId());

    }

    // @Test
    // public void getByAttributeTypeTest() throws Exception {
    // Route rte = this.service.getByAttributeType("hola");
    // assertNull(rte);
    // }

    @Test
    public void removeTest() throws Exception {
	service.removeObject(this.ct.getId());
	verify(this.routeRepository, Mockito.times(1)).deleteById(this.rt.getId());
    }

    @Test
    public void getByIdTest() throws Exception {
	when(this.routeRepository.findById(this.rt.getId())).thenReturn(java.util.Optional.ofNullable(this.rt));
	Route rte = this.service.getById(this.rt.getId());
	assertEquals(this.airportEnd, rte.getAirportEnd());
	assertEquals(this.airportBegin, rte.getAirportBegin());

	assertEquals(100, rte.getDistance());
	assertEquals(1, rte.getId());
    }

    // @Test
    // public void getByAttributeTypeRoute() throws Exception {
    // when(this.routeRepository.getAttributeByAirports(this.airportBegin.getIataCode(),
    // this.airportEnd.getIataCode())).thenReturn(java.util.Optional.ofNullable(this.rt));
    // Route rte =
    // this.service.getByAttributeTypeRoute(this.airportBegin.getIataCode(),
    // this.airportEnd.getIataCode());
    // assertEquals(this.airportEnd, rte.getAirportEnd());
    // assertEquals(this.airportBegin, rte.getAirportBegin());
    // assertEquals(1, rte.getEstimatedTime());
    // assertEquals(100, rte.getDistance());
    // assertEquals(1, rte.getId());
    // }
}