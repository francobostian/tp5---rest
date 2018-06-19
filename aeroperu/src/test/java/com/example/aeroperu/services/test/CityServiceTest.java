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
    City city = new City(1, "Buenos Aires", "BS", st);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    // @Test
    // public void addTest() throws Exception {
    // when(this.cityRepository.save(this.city)).thenReturn(this.city);
    // City cit = this.service.newObject(this.city);
    // assertEquals(1, cit.getId());
    // assertEquals("Buenos Aires", cit.getName());
    // assertEquals("BS", cit.getIataCode());
    // assertEquals(this.st, cit.getState());
    //
    // }
    //
    // @Test
    // public void removeTest() throws Exception {
    // service.removeObject(this.city.getId());
    // verify(this.cityRepository, times(1)).deleteById(this.city.getId());
    // }

    // @Test
    // public void getByIdTest() throws Exception {
    // when(this.cityRepository.findById(this.city.getId())).thenReturn(java.util.Optional.ofNullable(this.city));
    // City cit = this.service.getById(this.city.getId());
    // assertEquals(1, cit.getId());
    // assertEquals("Buenos Aires", cit.getName());
    // assertEquals("BS", cit.getIataCode());
    // assertEquals(this.st, cit.getState());
    // }

    @Test
    public void getByAttributeTypeTest() throws Exception {
	when(this.cityRepository.getAttribute(this.city.getIataCode())).thenReturn(this.city);
	City cit = this.service.getByAttributeType(this.city.getIataCode());
	assertEquals(1, cit.getId());
	assertEquals("Buenos Aires", cit.getName());
	assertEquals("BS", cit.getIataCode());
	assertEquals(this.st, cit.getState());
    }
}