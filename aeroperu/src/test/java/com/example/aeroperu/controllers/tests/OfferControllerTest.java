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
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.Airport;
import com.esq.models.Cabin;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.esq.models.State;
import com.example.aeroperu.controllers.OfferController;
import com.example.aeroperu.pojo.OfferPojoSetter;
import com.example.aeroperu.service.CabinService;
import com.example.aeroperu.service.OfferService;
import com.example.aeroperu.service.RouteService;

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferControllerTest {

    @Mock
    private OfferService offerService;
    @Mock
    private RouteService routeService;
    @Mock
    private CabinService cabinService;
    @InjectMocks
    private OfferController controller;
    Cabin cabin = new Cabin(1, "Economica");

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BS", st);
    City city2 = new City(1, "Rosario", "R", st);
    Airport airportBegin = new Airport(1, "AreolineasArgentinas", "ARG", city, -222, 222);
    Airport airportEnd = new Airport(2, "MDP", "MDP", city2, -222, 222);
    Route rt = new Route(1, airportBegin, airportEnd, 100);

    Offer offer = new Offer(rt, "24-06-2018", "24-08-2018", cabin, 1400);
    OfferPojoSetter pojo = new OfferPojoSetter(1, 1, 1, 1800);
    List<Offer> list = new ArrayList<Offer>();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	this.list.add(this.offer);
    }

    @Test
    public void addTest() {

	Offer offer = new Offer();
	Route route = routeService.getById(pojo.getRoute_id());
	Cabin cabin = cabinService.getById(pojo.getCabin_id());

	offer.setRoute(route);
	offer.setCabin(cabin);
	offer.setPrice(pojo.getPrice());
	offer.setFrom(pojo.getBeginning());
	offer.setUntil(pojo.getEnd());

	// Offer of = this.offerService.newOffer(offer);
	Mockito.when(this.controller.createOffer(pojo)).thenReturn(this.offer);
    }

    @Test
    public void getAllOffersTest() throws Exception {
	// Mockito.when(this.controller.getAllOffers()).thenReturn(this.list);
    }

    @Test
    public void getByOneOfferTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	// Offer tk = null;
	// assertNull(tk);
	// String name = this.cabin.getName();
	// long id = cabin.getId();
	// assertNotNull(name);
	// Mockito.when(this.offerService.getByAttributeType(name)).thenReturn(this.cabin);

	// tk = this.cabinService.getByAttributeType(name);
	// assertEquals(this.cabin, tk);
	// assertNotNull(tk);
	long id = 1;
	status = this.controller.getOfferById(id);
	assertEquals(new ResponseEntity(this.cabin, HttpStatus.OK), status);
    }

    @Test
    public void removeTest() {
	Long id = Long.valueOf(1);
	ResponseEntity ent = new ResponseEntity(HttpStatus.OK);
	assertNotNull(id);
	ResponseEntity status = this.controller.deleteOffer(id);
	assertEquals(ent, status);
    }

    @Test
    public void updateTest() {

	ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	Cabin getCab = new Cabin(1, "Economica");

	assertNotNull(this.cabin);
	Mockito.when(this.cabinService.getById(this.cabin.getId())).thenReturn(this.cabin);
	Cabin cab = this.cabinService.getById(this.cabin.getId());
	assertNotNull(cab);

	assertEquals(this.cabin, cab);
	Mockito.when(this.cabinService.newObject(this.cabin)).thenReturn(this.cabin);
	Cabin cb = this.cabinService.newObject(this.cabin);
	assertEquals(this.cabin, cb);
	// Mockito.when(this.controller.updateCabin(cabin.getId(),
	// this.cabin)).thenReturn(status);
	// status = this.controller.updateCabin(cabin.getId(), this.cabin);
	// assertEquals(new ResponseEntity(HttpStatus.OK), status);
	// this.controller.updateCabin(cabin.getId(), this.cabin);
	long id = 1;
	controller.updateOffer(id, pojo);
    }

    // @Test
    // public void updateTestExceptionCase1() {
    // this.controller.updateCabin(cabin.getId(), null);
    // }
}
