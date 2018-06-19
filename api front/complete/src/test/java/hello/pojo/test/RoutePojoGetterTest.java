package hello.pojo.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Route;
import com.esq.models.State;

import hello.pojo.RoutePojoGetter;

public class RoutePojoGetterTest {

    RoutePojoGetter route;
    RoutePojoGetter otherRoute;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Mar del Plata", "7600", state);
	Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
	Airport airportEnd = new Airport(2, "Ezeiza International route", "EZE", city, (float) 24.22, (float) 107.58);

	this.route = new RoutePojoGetter(new Route(1, airportBegin, airportEnd, 50));
	this.otherRoute = new RoutePojoGetter();
    }

    @Test
    public void testEqualsOK() {
	this.otherRoute = this.route;
	assertTrue("Checking equals", this.route.equals(this.otherRoute));
    }

}
