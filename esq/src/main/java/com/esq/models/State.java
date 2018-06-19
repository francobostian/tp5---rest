package com.esq.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "provincia")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode

@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    // @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.WRITE_ONLY)

    private long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "iata", nullable = false)
    private String iataCode;

    @JoinColumn(name = "id_pais")
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public State(long id, String name, String iataCode, Country country) {
	this.id = id;
	this.name = name;
	this.iataCode = iataCode;
	this.country = country;
    }

    public State(String name, String iataCode, Country country) {

	this.name = name;
	this.iataCode = iataCode;
	this.country = country;
    }

    public State() {
	super();
    }

}
