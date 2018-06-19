package hello.pojo.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.Cabin;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.esq.models.State;

import hello.pojo.OfferPojoGetter;

public class OfferPojoGetterTest {

    OfferPojoGetter offer;
    OfferPojoGetter otherOffer;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Mar del Plata", "7600", state);
	Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
	Airport airportEnd = new Airport(2, "Ezeiza International route", "EZE", city, (float) 24.22, (float) 107.58);
	Route route = new Route(1, airportBegin, airportEnd, 400);
	Cabin cabin = new Cabin(1, "Bussines");

	Offer offer = new Offer(route, "333", "4444", cabin, 2000);
	this.offer = new OfferPojoGetter(new Offer(route, "24-06-2018", "24-07-2018", cabin, 50));
	this.otherOffer = new OfferPojoGetter();
    }

    @Test
    public void testEqualsOK() {
	this.otherOffer = this.offer;
	assertTrue("Checking equals", this.offer.equals(this.otherOffer));
    }
}
