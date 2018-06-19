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
import com.esq.models.Cabin;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.esq.models.State;
import com.example.aeroperu.repo.OfferRepo;
import com.example.aeroperu.service.OfferService;

@RunWith(SpringJUnit4ClassRunner.class)
public class OfferServiceTest {

    @Mock
    private OfferRepo offerRepository;

    @InjectMocks
    private OfferService service;

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BS", st);
    City city2 = new City(1, "Rosario", "R", st);
    Airport airportBegin = new Airport(1, "AreolineasArgentinas", "ARG", city, -222, 222);
    Airport airportEnd = new Airport(2, "MDP", "MDP", city2, -222, 222);
    Route rt = new Route(1, airportBegin, airportEnd, 100);
    Cabin cabin = new Cabin(1, "Bussines");
    Offer offer = new Offer(rt, "", "", cabin, 2000);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	offer.setId(1);
    }

    @Test
    public void getALlTest() throws Exception {
	List<Offer> list = new ArrayList<Offer>();
	list.add(this.offer);
	list.add(this.offer);
	list.add(this.offer);
	when(this.offerRepository.findAll()).thenReturn(list);
	List<Offer> dao = this.service.getAll();
	assertEquals(3, list.size());
    }

    @Test
    public void addTest() throws Exception {
	when(this.offerRepository.save(this.offer)).thenReturn(this.offer);
	Offer offer = this.service.newOffer(this.offer);
	assertEquals(this.rt, offer.getRoute());
	assertEquals(this.cabin, offer.getCabin());

	assertEquals(2000, offer.getPrice());
	// assertEquals(1, offer.getId());

    }

    // @Test
    // public void getByAttributeTypeTest() throws Exception {
    // Route rte = this.service.getByAttributeType("hola");
    // assertNull(rte);
    // }

    @Test
    public void removeTest() throws Exception {
	service.removeOffer(this.offer.getId());
	verify(this.offerRepository, Mockito.times(1)).deleteById(this.rt.getId());
    }

    @Test
    public void getByIdTest() throws Exception {
	when(this.offerRepository.findById(this.offer.getId())).thenReturn(java.util.Optional.ofNullable(this.offer));
	Offer offer = this.service.getById(this.offer.getId());
	assertEquals(this.cabin, offer.getCabin());
	assertEquals(this.rt, offer.getRoute());

	assertEquals(2000, offer.getPrice());
	assertEquals(1, offer.getId());
    }
}