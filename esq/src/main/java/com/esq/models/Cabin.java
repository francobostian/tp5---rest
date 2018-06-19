package com.esq.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cabina")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cabin {

    @Id
    @GeneratedValue
    // @JsonInclude(Include.NON_NULL)
    @JsonProperty(access = Access.WRITE_ONLY)

    @Column(name = "id", nullable = false)

    private long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    public Cabin(long id, String name) {
	this.id = id;
	this.name = name;

    }

    public Cabin() {
	super();
	// TODO Auto-generated constructor stub
    }

}
