package com.esq.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "aeropuerto")

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Airport {

    @Id
    @GeneratedValue
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "iata", nullable = false)
    private String iataCode;

    @Column(name = "nombre", nullable = false)
    private String name;

    @JoinColumn(name = "id_ciudad")
    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnore
    @JsonInclude(Include.NON_NULL)
    private City city;
    @JsonInclude(Include.NON_NULL)
    // @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "langitud", nullable = false)
    private float latitude;
    @JsonInclude(Include.NON_NULL)
    // @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "latitud", nullable = false)
    private float longitude;

    public Airport(long id, String name, String iataCode, City city, float latitude, float longitude) {
	this.id = id;
	this.name = name;
	this.iataCode = iataCode;
	this.city = city;
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public Airport(String name, String iataCode, City city, float latitude, float longitude) {
	this.name = name;
	this.iataCode = iataCode;
	this.city = city;
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public Airport() {
	super();
	// TODO Auto-generated constructor stub
    }

}
