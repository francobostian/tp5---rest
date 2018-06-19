package com.example.aeroperu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esq.models.City;
import com.example.aeroperu.repo.CityRepo;

@Service
public class CityService {

    @Autowired
    private CityRepo cityRepository;

    public City getByAttributeType(String iataCode) {
	return this.cityRepository.getAttribute(iataCode);
    }

}
