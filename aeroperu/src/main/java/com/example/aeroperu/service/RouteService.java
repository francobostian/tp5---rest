package com.example.aeroperu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esq.models.Route;
import com.example.aeroperu.repo.RouteRepo;

@Service
public class RouteService {

    @Autowired
    private RouteRepo routeRepository;

    public List<Route> getAll() {
	return this.routeRepository.findAll();
    }

    public Route getById(Long id) {
	Route route = null;
	Optional<Route> routeOptional = this.routeRepository.findById(id);
	if (routeOptional.isPresent()) {
	    route = routeOptional.get();
	}
	return route;
    }

    public Route newSave(Route value) {
	if (value != null) {

	    this.routeRepository.save(value);
	}
	return value;
    }

    public void removeObject(Long id) {
	this.routeRepository.deleteById(id);
    }

}
