package hello.controller.test;

import static org.junit.Assert.assertEquals;

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
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;

import hello.controller.AirportController;
import hello.pojo.AirportPojo;
import hello.pojo.AirportViewPojo;

@RunWith(SpringJUnit4ClassRunner.class)
public class AirportControllerTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    AirportController controller;

    Country country = new Country(1, "Argentina", "ARG");
    State state = new State(1, "Buenos Aires", "BA", country);
    City city = new City(1, "Buenos Aires", "CABA", state);
    Airport air = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
    AirportPojo airPojo = new AirportPojo(air);
    AirportPojo[] airports = { airPojo, airPojo };
    List<AirportPojo> pojos = new ArrayList<>();
    List<AirportViewPojo> pojosV = new ArrayList<AirportViewPojo>();

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	pojos.add(airPojo);
	pojos.add(airPojo);
	pojosV.add(new AirportViewPojo(airPojo));
    }

    @Test
    public void getAirportsTest() {
	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Buenos Aires", "CABA", state);
	Airport air = new Airport(1, "Jorge Newbery", "AEP", city, 555, 888);
	AirportPojo airPojo = new AirportPojo(air);

	Mockito.when(this.restTemplate.getForObject("http://localhost:8888/airport/", AirportPojo[].class))
		.thenReturn(airports);

	assertEquals(this.controller.getAirports(), airports);
    }

}
