package com.example.aeroperu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esq.models.Airport;
import com.esq.models.Route;
import com.example.aeroperu.pojo.RoutePojoGetter;
import com.example.aeroperu.pojo.RoutePojoSetter;
import com.example.aeroperu.service.AirportService;
import com.example.aeroperu.service.RouteService;

@RestController
@RequestMapping(value = "/route", produces = "Application/Json")
public class RouteController {

    @Autowired
    RouteService routeDAO;
    @Autowired
    AirportService airportService;

    /* to save an port */
    @PostMapping("/")
    public Route createRoute(@Valid @RequestBody RoutePojoSetter airport) {
	Route route = new Route();
	Airport origin = new Airport();
	Airport destination = new Airport();
	// System.err.println(air.getOrigin());
	// System.err.println(air.getDestino());
	origin = airportService.getByAttributeType(airport.getOrigin());
	destination = airportService.getByAttributeType(airport.getDestination());
	route.setAirportBegin(origin);
	route.setAirportEnd(destination);
	route.setDistance(airport.getDistance());

	return routeDAO.newSave(route);
    }

    /* get all port */
    @GetMapping("/")
    public List<RoutePojoGetter> getAllRoutes() {
	List<Route> routes = (List<Route>) routeDAO.getAll();

	List<RoutePojoGetter> pojos = new ArrayList<RoutePojoGetter>();

	for (Route r : routes) {
	    pojos.add(new RoutePojoGetter(r));
	}

	return pojos;
    }

    /* get Route by airid */
    @GetMapping("/{id}")
    public ResponseEntity<RoutePojoGetter> getRouteById(@PathVariable(value = "id") Long id) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Route route = routeDAO.getById(id);

	if (route == null) {
	    return ResponseEntity.notFound().build();
	}

	RoutePojoGetter pojo = new RoutePojoGetter(route);

	// return ResponseEntity.ok().body(air);
	status = new ResponseEntity<RoutePojoGetter>(pojo, HttpStatus.OK);
	return status;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> updateRoute(@PathVariable(value = "id") Long id,
	    @Valid @RequestBody RoutePojoSetter routeDetails) {

	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	Airport origin = airportService.getByAttributeType(routeDetails.getOrigin());
	Airport destination = airportService.getByAttributeType(routeDetails.getDestination());

	Route route = routeDAO.getById(id);
	if (route == null) {
	    return ResponseEntity.notFound().build();
	}

	Route port = new Route();
	port.setId(id);
	port.setAirportBegin(origin);
	port.setAirportEnd(destination);
	port.setDistance(routeDetails.getDistance());

	Route updateRoute = routeDAO.newSave(port);
	// return ResponseEntity.ok().body(updateRoute);
	// return ResponseEntity.badRequest().build();
	status = new ResponseEntity<Route>(updateRoute, HttpStatus.OK);
	return status;

    }

    /* Delete an Route */
    @DeleteMapping("/{id}")
    public ResponseEntity<Route> deleteRoute(@PathVariable(value = "id") Long airid) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	Route air = routeDAO.getById(airid);
	if (air == null) {
	    return ResponseEntity.notFound().build();
	}
	routeDAO.removeObject(airid);
	status = new ResponseEntity(HttpStatus.OK);
	return status;
	// return ResponseEntity.ok().build();

    }

}