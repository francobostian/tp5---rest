package com.example.aeroperu.services.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;
import com.example.aeroperu.repo.CityRepo;
import com.example.aeroperu.service.CityService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CityServiceTest {

    @Mock
    private CityRepo cityRepository;

    @InjectMocks
    private CityService service;

    Country ct = new Country(1, "Argentina", "ARG");
    State st = new State(1, "state", "ARG", ct);
    City city = new City(1, "Buenos Aires", "BUE", st);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByAttributeTypeTest() throws Exception {
	when(this.cityRepository.getAttribute(this.city.getIataCode())).thenReturn(this.city);
	City cit = this.service.getByAttributeType(this.city.getIataCode());
	assertEquals(1, cit.getId());
	assertEquals("Buenos Aires", cit.getName());
	assertEquals("BUE", cit.getIataCode());
	assertEquals(this.st, cit.getState());
    }
}