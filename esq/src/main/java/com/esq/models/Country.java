package com.esq.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pais")
// @EntityListeners(AuditingEntityListener.class)

@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    // @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.WRITE_ONLY)

    private long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "iso", nullable = false)
    private String isoCode;

    public Country(long id, String name, String isoCode) {
	this.id = id;
	this.name = name;
	this.isoCode = isoCode;
    }

    public Country(String name, String isoCode) {

	this.name = name;
	this.isoCode = isoCode;
    }

    @Override
    public String toString() {
	String to = "";
	to = this.name + " (" + this.isoCode + ")";

	return to;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Country other = (Country) obj;
	if (id != other.getId())
	    return false;
	if (isoCode == null) {
	    if (other.isoCode != null)
		return false;
	} else if (!isoCode.equals(other.isoCode))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

    public Country() {
	super();
	// TODO Auto-generated constructor stub
    }

}
