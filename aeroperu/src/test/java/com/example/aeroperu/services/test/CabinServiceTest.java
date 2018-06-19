package com.example.aeroperu.services.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.esq.models.Cabin;
import com.example.aeroperu.repo.CabinRepo;
import com.example.aeroperu.service.CabinService;

public class CabinServiceTest {

    @Mock
    private CabinRepo cabinRepository;

    @InjectMocks
    private CabinService service;

    Cabin cabin = new Cabin(1, "Economica");

    @Before
    public void setUp() {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getALlTest() {
	List<Cabin> listCabin = new ArrayList<Cabin>();
	listCabin.add(this.cabin);
	listCabin.add(this.cabin);
	listCabin.add(this.cabin);
	Mockito.when(this.cabinRepository.findAll()).thenReturn(listCabin);
	List<Cabin> dao = this.service.getAll();
	assertEquals(3, listCabin.size());
    }

    @Test
    public void addTest() {
	Mockito.when(this.cabinRepository.save(this.cabin)).thenReturn(this.cabin);
	Cabin cab = this.service.newObject(this.cabin);
	assertEquals(1, cab.getId());
	assertEquals("Economica", cab.getName());

    }

    @Test
    public void removeTest() {
	service.removeObject(this.cabin.getId());
	Mockito.verify(this.cabinRepository, Mockito.times(1)).deleteById(this.cabin.getId());
    }

    @Test
    public void getByIdTest() {
	Mockito.when(this.cabinRepository.findById(this.cabin.getId())).thenReturn(Optional.ofNullable(this.cabin));
	Cabin cab = this.service.getById(this.cabin.getId());
	assertEquals(1, cab.getId());
	assertEquals("Economica", cab.getName());

    }

    @Test
    public void getByAttributeTypeTest() {
	Mockito.when(this.cabinRepository.getAttribute(this.cabin.getName())).thenReturn(this.cabin);
	Cabin cab = this.service.getByAttributeType(this.cabin.getName());
	assertEquals(1, cab.getId());
	assertEquals("Economica", cab.getName());

    }
}