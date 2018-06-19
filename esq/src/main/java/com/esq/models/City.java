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
@Table(name = "ciudad")
@EqualsAndHashCode

@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class City {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    // @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.WRITE_ONLY)

    private long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "iata", nullable = false)
    private String iataCode;

    @JoinColumn(name = "id_estado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonInclude(Include.NON_NULL)
    private State state;

    public City(long id, String name, String iataCode, State state) {
	this.id = id;
	this.name = name;
	this.iataCode = iataCode;
	this.state = state;
    }

    public City(String name, String iataCode, State state) {
	this.name = name;
	this.iataCode = iataCode;
	this.state = state;
    }

    public City() {
	super();
	// TODO Auto-generated constructor stub
    }

}
