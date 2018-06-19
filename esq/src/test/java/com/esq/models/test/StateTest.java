package com.esq.models.test;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Country;
import com.esq.models.State;

import junit.framework.TestCase;

public class StateTest extends TestCase {

    State state;
    State otherState;

    @Before
    public void setUp() {

	Country country = new Country(1, "Argentina", "ARG");
	this.state = new State(1, "Buenos Aires", "BA", country);
	this.otherState = new State("Córdoba", "COR", country);
    }

    @Test
    public void testEqualsNull() {
	boolean value = this.state.equals(null);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOtherObject() {
	boolean value = this.state.equals("String");
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsNullAttributes() {
	this.otherState.setId(0);
	this.otherState.setName(null);
	this.otherState.setCountry(null);
	this.otherState.setIataCode(null);

	boolean value = this.state.equals(this.otherState);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsDifferentAttributes() {
	boolean value = this.state.equals(this.otherState);
	assertEquals("Checking equals", value, false);
    }

    @Test
    public void testEqualsOK() {
	this.otherState = this.state;

	boolean value = this.state.equals(this.otherState);
	assertEquals("Checking equals", value, true);
    }

}
