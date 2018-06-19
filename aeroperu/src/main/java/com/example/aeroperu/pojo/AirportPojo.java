package com.example.aeroperu.pojo;

import com.esq.models.Airport;
import com.esq.models.Country;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AirportPojo {

    @JsonProperty
    private String iata;
    @JsonProperty
    private String name;
    @JsonProperty
    private CityPojo city;
    @JsonProperty
    private Country country;
    @JsonProperty
    private float longitude;
    @JsonProperty
    private float latitude;

    public AirportPojo(Airport airport) {
	super();
	this.iata = airport.getIataCode();
	this.city = new CityPojo(airport.getCity());
	this.country = airport.getCity().getState().getCountry();
	this.name = airport.getName();
	this.longitude = airport.getLongitude();
	this.latitude = airport.getLatitude();
    }
}
