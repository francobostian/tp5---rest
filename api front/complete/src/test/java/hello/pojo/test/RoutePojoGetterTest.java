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
	State state = new State(1, "Buenos Aires", "BUE", country);
	City city = new City(1, "Buenos Aires", "CABUE", state);
	Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, 123, 547);
	Airport airportEnd = new Airport(2, "Ezeiza", "EZE", city, 654, 789);

	this.route = new RoutePojoGetter(new Route(1, airportBegin, airportEnd, 50));
	this.otherRoute = new RoutePojoGetter();
    }

    @Test
    public void testEqualsOK() {
	this.otherRoute = this.route;
	assertTrue("Checking equals", this.route.equals(this.otherRoute));
    }

}
