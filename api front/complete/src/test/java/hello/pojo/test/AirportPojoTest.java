package hello.pojo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;

import hello.pojo.AirportPojo;
import hello.pojo.CityPojo;

public class AirportPojoTest {

    AirportPojo airport;
    AirportPojo otherAirport;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Buenos Aires", "CABA", state);
	CityPojo cityPojo = new CityPojo(city);
	Airport air = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
	this.airport = new AirportPojo(air);

	this.otherAirport = null;

    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.airport.equals(this.otherAirport);
	assertEquals("Checking equals", value, false);
    }

}
