package com.esq.models.test;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Route;
import com.esq.models.State;

import junit.framework.TestCase;

public class RouteTest extends TestCase {

    Route route;
    Route otherRoute;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Buenos Aires", "CABA", state);
	Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
	Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float) 24.22, (float) 107.58);

	this.route = new Route(1, airportBegin, airportEnd, 50);
	this.otherRoute = new Route(2, airportEnd, airportBegin, 50);
    }

    @Test
    public void testEqualsNull() {
	boolean value = this.route.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.route.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherRoute.setId(0);
	this.otherRoute.setAirportBegin(null);
	this.otherRoute.setAirportEnd(null);
	this.otherRoute.setDistance(0);

	boolean value = this.route.equals(this.otherRoute);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.route.equals(this.otherRoute);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherRoute = this.route;

	boolean value = this.route.equals(this.otherRoute);
	assertEquals("Checking equals", value, true);
    }

}