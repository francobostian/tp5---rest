package hello.pojo.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.esq.models.Cabin;

import hello.pojo.CabinPojo;

public class CabinPojoTest {

    CabinPojo cabin;
    CabinPojo otherCabin;

    @Before
    public void setUp() {
	this.cabin = new CabinPojo(new Cabin(1, "Económico"));
	this.otherCabin = new CabinPojo();
    }

    @Test
    public void testDifferentAttributes() {
	boolean value = this.cabin.equals(this.otherCabin);
	assertEquals("Checking equals", value, false);
    }

    // @Test
    // public void testEqualsBad() {
    // this.otherCabin = new CabinPojo(new Cabin(2, "VIP"));
    //
    // assertFalse("Checking equals", this.cabin.equals(null));
    // assertFalse("Checking equals", this.cabin.equals("String"));
    // assertFalse("Checking equals", this.cabin.equals(this.otherCabin));
    // }
}
