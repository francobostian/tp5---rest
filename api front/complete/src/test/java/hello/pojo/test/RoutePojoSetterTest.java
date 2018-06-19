package hello.pojo.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import hello.pojo.RoutePojoSetter;

public class RoutePojoSetterTest {

    RoutePojoSetter route;
    RoutePojoSetter otherRoute;

    @Before
    public void setUp() {

	this.route = new RoutePojoSetter(1, "333", "4444", 300);
	this.otherRoute = new RoutePojoSetter();

    }

    @Test
    public void testEqualsOK() {
	this.otherRoute = this.route;
	assertTrue("Checking equals", this.route.equals(this.otherRoute));
    }
}
