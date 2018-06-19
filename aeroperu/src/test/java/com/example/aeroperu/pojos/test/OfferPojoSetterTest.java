package com.example.aeroperu.pojos.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.aeroperu.pojo.OfferPojoSetter;

public class OfferPojoSetterTest {

    OfferPojoSetter offer;
    OfferPojoSetter otherOffer;

    @Before
    public void setUp() {

	this.offer = new OfferPojoSetter(1, 1, 1, 2000);
	this.otherOffer = new OfferPojoSetter();

    }

    @Test
    public void testEqualsOK() {
	this.otherOffer = this.offer;
	assertTrue("Checking equals", this.offer.equals(this.otherOffer));
    }
}
