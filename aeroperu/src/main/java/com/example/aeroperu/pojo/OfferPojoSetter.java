package com.example.aeroperu.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OfferPojoSetter {

    @JsonProperty
    private long id;
    @JsonProperty
    private long route_id;
    @JsonProperty
    private long cabin_id;
    @JsonProperty
    private long price;
    @JsonProperty
    private String beginning;
    @JsonProperty
    private String end;

    public OfferPojoSetter(long id, long route_id, long cabin_id, long price) {
	super();
	this.id = id;
	this.route_id = route_id;
	this.cabin_id = cabin_id;
	this.price = price;
    }

    public OfferPojoSetter(long route_id, long cabin_id, long price, String beginning, String end) {
	super();
	// this.id = id;
	this.route_id = route_id;
	this.cabin_id = cabin_id;
	this.price = price;
	this.beginning = beginning;
	this.end = end;
    }

}
