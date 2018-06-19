package hello.controller.test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
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
import org.springframework.web.client.RestTemplate;

import com.esq.models.Airport;
import com.esq.models.Cabin;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.esq.models.State;

import hello.controller.OfferController;
import hello.pojo.OfferPojoGetter;
import hello.pojo.OfferViewPojo;

@RunWith(SpringJUnit4ClassRunner.class)

public class OfferControllerTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    OfferController controller;

    Cabin cabin = new Cabin(1, "Economica");

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BS", st);
    City city2 = new City(1, "Rosario", "R", st);
    Airport airportBegin = new Airport(1, "AreolineasArgentinas", "ARG", city, -222, 222);
    Airport airportEnd = new Airport(2, "MDP", "MDP", city2, -222, 222);
    Route route = new Route(1, airportBegin, airportEnd, 100);

    Offer offer = new Offer(route, "24-06-2018", "24-08-2018", cabin, 1400);
    OfferPojoGetter pojo = new OfferPojoGetter(offer);
    OfferViewPojo viewPojo = new OfferViewPojo(pojo);
    OfferPojoGetter[] arrayPojo = { pojo, pojo };
    List<Offer> list = new ArrayList<Offer>();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getOffersTest() throws ParseException {

	Mockito.when(this.restTemplate.getForObject("http://localhost:8888/offer/", OfferPojoGetter[].class))
		.thenReturn(arrayPojo);

	assertEquals(this.controller.getOffers("333", "4444", "29-06-2018"), arrayPojo);
    }

    @Test
    public void getOffersFilterCabinTest() throws ParseException {

	Mockito.when(this.restTemplate.getForObject("http://localhost:8888/offer/", OfferPojoGetter[].class))
		.thenReturn(arrayPojo);
	long cabinId = 1;
	assertEquals(this.controller.getOffersFilterCabin("333", "4444", "29-06-2018", cabinId), arrayPojo);
    }
}
