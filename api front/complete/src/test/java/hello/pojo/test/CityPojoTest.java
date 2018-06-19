package hello.pojo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;

import hello.pojo.CityPojo;

public class CityPojoTest {

    CityPojo city;
    CityPojo otherCity;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	this.city = new CityPojo(new City(1, "Mar del Plata", "7600", state));
	this.otherCity = new CityPojo();
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.city.equals(this.otherCity);
	assertEquals("Comprobando igualdad", value, false);
    }
}
