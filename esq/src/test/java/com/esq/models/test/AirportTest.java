package com.esq.models.test;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;

import junit.framework.TestCase;

public class AirportTest extends TestCase {

    Airport airport;
    Airport otherAirport;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BUE", country);
	City city = new City(1, "Buenos Aires", "CABUE", state);

	this.airport = new Airport(1, "Jorge Newbery", "AEP", city, 333, 777);
	this.otherAirport = new Airport("Ezeiza", "EZE", city, 888, 990);

    }

    @Test
    public void testEqualsNull() {
	boolean value = this.airport.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.airport.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherAirport.setId(0);
	this.otherAirport.setName(null);
	this.otherAirport.setCity(null);
	this.otherAirport.setIataCode(null);
	this.otherAirport.setLatitude(0);
	this.otherAirport.setLongitude(0);

	boolean value = this.airport.equals(this.otherAirport);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.airport.equals(this.otherAirport);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherAirport = this.airport;

	boolean value = this.airport.equals(this.otherAirport);
	assertEquals("Checking equals", value, true);
    }

}