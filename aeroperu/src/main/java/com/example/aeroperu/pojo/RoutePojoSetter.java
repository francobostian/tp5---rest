package com.example.aeroperu.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

//import lombok.Setter;
@Getter
@NoArgsConstructor
public class RoutePojoSetter {

    @JsonProperty
    private long id;
    @JsonProperty
    private String origin;
    @JsonProperty
    private String destination;
    @JsonProperty
    private int distance;

    public RoutePojoSetter(long id, String origin, String destination, int distance) {
	super();
	this.id = id;
	this.origin = origin;
	this.destination = destination;
	this.distance = distance;
    }

}
