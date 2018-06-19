package com.esq.models.test;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.State;

import junit.framework.TestCase;

public class CityTest extends TestCase {

    City city;
    City otherCity;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BUE", country);

	this.city = new City(1, "Mar del Plata", "MDQ", state);
	this.otherCity = new City("Ayacucho", "AYA", state);
    }

    @Test
    public void testEqualsNull() {
	boolean value = this.city.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.city.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherCity.setId(0);
	this.otherCity.setName(null);
	this.otherCity.setState(null);
	this.otherCity.setIataCode(null);

	boolean value = this.city.equals(this.otherCity);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.city.equals(this.otherCity);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherCity = this.city;

	boolean value = this.city.equals(this.otherCity);
	assertEquals("Checking equals", value, true);
    }

}