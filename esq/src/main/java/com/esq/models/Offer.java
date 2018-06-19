package com.esq.models;

import javax.persistence.CascadeType;
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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "precio")
// @EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Offer {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    // @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.WRITE_ONLY)

    private long id;

    @JoinColumn(name = "ruta")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonInclude(Include.NON_EMPTY)
    // @JsonProperty(access = Access.WRITE_ONLY)
    private Route route;

    @Column(name = "desde", nullable = false)
    private String from;

    @Column(name = "hasta", nullable = false)
    private String until;

    @JoinColumn(name = "id_cabina")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cabin cabin;

    @Column(name = "precio", nullable = false)
    private long price;

    public Offer(Route route, String string, String string2, Cabin cabin, long price) {
	super();
	this.route = route;
	this.from = string;
	this.until = string2;
	this.cabin = cabin;
	this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Offer other = (Offer) obj;
	if (cabin == null) {
	    if (other.cabin != null)
		return false;
	} else if (!cabin.equals(other.cabin))
	    return false;
	if (from == null) {
	    if (other.from != null)
		return false;
	} else if (!from.equals(other.from))
	    return false;
	if (id != other.id)
	    return false;
	if (price != other.price)
	    return false;
	if (route == null) {
	    if (other.route != null)
		return false;
	} else if (!route.equals(other.route))
	    return false;
	if (until == null) {
	    if (other.until != null)
		return false;
	} else if (!until.equals(other.until))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	String to = "", routeString = "null", cabinString = "null";

	if (this.route != null)
	    routeString = this.route.toString();

	if (this.cabin != null)
	    cabinString = this.cabin.toString();

	to = routeString + " - Cabin: " + cabinString + " - Offer will be from: " + this.from + " to: " + this.until
		+ " the price is: " + this.price;

	return to;
    }

    public Offer(String from, String until, Cabin cabin, long price) {
	super();
	this.from = from;
	this.until = until;
	this.cabin = cabin;
	this.price = price;
    }

    public Offer() {
	super();
	// TODO Auto-generated constructor stub
    }

}
