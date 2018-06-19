package com.example.aeroperu.controllers.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.esq.models.Cabin;
import com.example.aeroperu.controllers.CabinController;
import com.example.aeroperu.pojo.CabinPojo;
import com.example.aeroperu.service.CabinService;

@RunWith(SpringJUnit4ClassRunner.class)
public class CabinControllerTest {

    @Mock
    private CabinService cabinService;
    @InjectMocks
    private CabinController controller;

    Cabin cabin = new Cabin(1, "Economica");
    List<Cabin> listCabin = new ArrayList<Cabin>();

    List<CabinPojo> listCabinPojo = new ArrayList<CabinPojo>();
    CabinPojo pojo = new CabinPojo(cabin);

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
	this.listCabin.add(this.cabin);
	listCabinPojo.add(pojo);
    }

    @Test
    public void addTest() {

	Cabin cab = this.cabinService.newObject(cabin);
	Mockito.when(this.controller.createCabin(cab)).thenReturn(this.cabin);
    }

    @Test
    public void getAllAirportTest() throws Exception {
	Mockito.when(this.controller.getAllCabins()).thenReturn(this.listCabinPojo);
    }

    @Test
    public void getByOneCabinTest() {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Cabin tk = null;
	assertNull(tk);
	String name = this.cabin.getName();
	long id = cabin.getId();
	assertNotNull(name);
	Mockito.when(this.cabinService.getByAttributeType(name)).thenReturn(this.cabin);

	tk = this.cabinService.getByAttributeType(name);
	assertEquals(this.cabin, tk);
	assertNotNull(tk);
	status = this.controller.getCabinBy(id);
	assertEquals(new ResponseEntity(this.pojo, HttpStatus.OK), status);
    }

    @Test
    public void removeTest() {
	Long id = Long.valueOf(1);
	ResponseEntity ent = new ResponseEntity(HttpStatus.OK);
	assertNotNull(id);
	ResponseEntity status = this.controller.deleteCabin(id);
	assertEquals(ent, status);
    }

    @Test
    public void updateTest() {

	ResponseEntity status = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
	Cabin getCab = new Cabin(1, "Economica");

	assertNotNull(this.cabin);
	Mockito.when(this.cabinService.getById(this.cabin.getId())).thenReturn(this.cabin);
	Cabin cab = this.cabinService.getById(this.cabin.getId());
	assertNotNull(cab);

	assertEquals(this.cabin, cab);
	Mockito.when(this.cabinService.newObject(this.cabin)).thenReturn(this.cabin);
	Cabin cb = this.cabinService.newObject(this.cabin);
	assertEquals(this.cabin, cb);
	// Mockito.when(this.controller.updateCabin(cabin.getId(),
	// this.cabin)).thenReturn(status);
	// status = this.controller.updateCabin(cabin.getId(), this.cabin);
	// assertEquals(new ResponseEntity(HttpStatus.OK), status);
	this.controller.updateCabin(cabin.getId(), this.cabin);
    }

    @Test
    public void updatenull() {
	this.controller.updateCabin(cabin.getId(), null);
    }

    @Test
    public void getByOneCabinTestException() {
	ResponseEntity status1 = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

	// Mockito.when(this.cabinService.getByAttributeType(this.cabin.getName())).thenThrow(Exception.class);
	long l = 1;
	status1 = this.controller.getCabinBy(l);

    }
}
