package com.example.aeroperu.pojo;

import com.esq.models.Route;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoutePojoGetter {
    @JsonProperty
    private AirportPojo origin;
    @JsonProperty
    private AirportPojo destination;
    @JsonProperty
    private int distance;

    public RoutePojoGetter(Route route) {
	super();
	this.origin = new AirportPojo(route.getAirportBegin());
	this.destination = new AirportPojo(route.getAirportEnd());
	this.distance = route.getDistance();
    }
}
