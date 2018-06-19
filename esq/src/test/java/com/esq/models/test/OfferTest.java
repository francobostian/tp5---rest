package com.esq.models.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Airport;
import com.esq.models.Cabin;
import com.esq.models.City;
import com.esq.models.Country;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.esq.models.State;

import junit.framework.TestCase;

public class OfferTest extends TestCase {

    Offer offer;
    Offer otherOffer;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	State state = new State(1, "Buenos Aires", "BA", country);
	City city = new City(1, "Buenos Aires", "CABA", state);
	Airport airportBegin = new Airport(1, "Jorge Newbery", "AEP", city, (float) 23.14, (float) 108.11);
	Airport airportEnd = new Airport(2, "Ezeiza International Airport", "EZE", city, (float) 24.22, (float) 107.58);
	Route route = new Route(1, airportBegin, airportEnd, 50);
	Cabin cabinA = new Cabin(1, "Business");
	Cabin cabinB = new Cabin(2, "First Class");

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	Date date = new Date();

	DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
	Date date2 = new Date();

	this.offer = new Offer(route, (dateFormat.format(date)), dateFormat2.format(date2), cabinA, (long) 200.00);
	this.otherOffer = new Offer((dateFormat.format(date)), dateFormat2.format(date2), cabinA, (long) 200.00);

    }

    @Test
    public void testToStringOK() {
	String value = this.offer.toString();
	assertEquals("Checking toString", value,
		"Jorge Newbery (AEP) - Buenos Aires (CABA) - Buenos Aires (BA) - Argentina (ARG) to Ezeiza International Airport (EZE) - Buenos Aires (CABA) - Buenos Aires (BA) - Argentina (ARG) - Cabin: Business - Offer will be from: 2018/06/08 to: 2018/06/08 the price is: 200");

    }

    @Test
    public void testToStringNull() {
	this.offer.setCabin(null);
	String value = this.offer.toString();

	assertEquals("Checking toString", value,
		"Jorge Newbery (AEP) - Buenos Aires (CABA) - Buenos Aires (BA) - Argentina (ARG) to Ezeiza International Airport (EZE) - Buenos Aires (CABA) - Buenos Aires (BA) - Argentina (ARG) - Cabin: null - Offer will be from: 2018/06/08 to: 2018/06/08 the price is: 200");
    }

    @Test
    public void testEqualsNull() {
	boolean value = this.offer.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.offer.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherOffer.setId(0);
	this.otherOffer.setCabin(null);
	this.otherOffer.setFrom(null);
	this.otherOffer.setUntil(null);
	this.otherOffer.setPrice(0);
	this.otherOffer.setRoute(null);
	boolean value = this.offer.equals(this.otherOffer);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.offer.equals(this.otherOffer);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherOffer = this.offer;

	boolean value = this.offer.equals(this.otherOffer);
	assertEquals("Checking equals", value, true);
    }

}