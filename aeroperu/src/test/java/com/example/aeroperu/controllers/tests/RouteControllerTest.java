package com.example.aeroperu.controllers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Route;
import com.esq.models.State;
import com.example.aeroperu.controllers.RouteController;
import com.example.aeroperu.pojo.RoutePojoGetter;
import com.example.aeroperu.pojo.RoutePojoSetter;
import com.example.aeroperu.service.AirportService;
import com.example.aeroperu.service.RouteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RouteControllerTest {
    @Mock
    private RouteService service;
    @Mock
    private AirportService airportService;
    @InjectMocks
    private RouteController controller;

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BS", st);
    City city2 = new City(1, "Rosario", "R", st);
    Airport airportBegin = new Airport(1, "AreolineasArgentinas", "ARG", city, -222, 222);
    Airport airportEnd = new Airport(2, "MDP", "MDP", city2, -222, 222);
    Route rt = new Route(1, airportBegin, airportEnd, 100);
    RoutePojoSetter pojo = new RoutePojoSetter(1, "201", "333", 400);

    RoutePojoGetter pojoGet = new RoutePojoGetter(rt);
    List<Route> rutas = new ArrayList<Route>();
    List<RoutePojoGetter> gett = new ArrayList<>();

    @Before
    public void setUp() {
	// MockitoAnnotations.initMocks(this);
	this.rutas.add(this.rt);
	gett.add(pojoGet);
    }

    @Test
    public void addTest() {

	assertEquals(this.airportBegin, this.rt.getAirportBegin());
	assertEquals(this.airportEnd, this.rt.getAirportEnd());

	String iata1 = this.rt.getAirportBegin().getIataCode();
	String iata2 = this.rt.getAirportEnd().getIataCode();

	Mockito.when(this.airportService.getByAttributeType(iata1)).thenReturn(this.airportBegin);
	Mockito.when(this.airportService.getByAttributeType(iata2)).thenReturn(this.airportEnd);

	Mockito.when(this.service.newSave(this.rt)).thenReturn(this.rt);
	Route ruta = this.service.newSave(this.rt);
	assertEquals(this.rt, ruta);

	Mockito.when(this.controller.createRoute(this.pojo)).thenReturn(rt);
    }

    @Test
    public void updateTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	long id = pojo.getId();
	// Mockito.when(this.controller.updateRoute(id,
	// pojo)).thenReturn(status);

	ResponseEntity statusOk = this.controller.updateRoute(rt.getId(), this.pojo);
    }

    @Test
    public void deleteAirportTest() {
	// Long id = Long.valueOf(1);
	ResponseEntity ent = new ResponseEntity(HttpStatus.OK);
	assertNotNull(rt.getId());
	// System.err.println(air.getId());
	// System.out.flush();

	ResponseEntity status = this.controller.deleteRoute(rt.getId());
	assertEquals(ent, status);

    }

    @Test
    public void getAllAirportTest() {
	Mockito.when(this.controller.getAllRoutes()).thenReturn(this.gett);
    }

    @Test
    public void getByOneCabinTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	long id = rt.getId();

	status = this.controller.getRouteById(id);
	assertEquals(new ResponseEntity(this.rt, HttpStatus.OK), status);
    }
}
