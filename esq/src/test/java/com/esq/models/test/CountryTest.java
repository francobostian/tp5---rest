package com.esq.models.test;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Country;

import junit.framework.TestCase;

public class CountryTest extends TestCase {

    Country country;
    Country otherCountry;

    @Before
    public void setUp() {
	this.country = new Country(1, "Argentina", "ARG");
	this.otherCountry = new Country("Uruguay", "URU");
    }

    @Test
    public void testEqualsNull() {
	boolean value = this.country.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.country.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherCountry.setId(0);
	this.otherCountry.setName(null);
	this.otherCountry.setIsoCode(null);

	boolean value = this.country.equals(otherCountry);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.country.equals(this.otherCountry);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherCountry = this.country;

	boolean value = this.country.equals(otherCountry);
	assertEquals("Checking equals", value, true);
    }

}