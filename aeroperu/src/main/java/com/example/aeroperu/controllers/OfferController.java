package com.example.aeroperu.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esq.models.Cabin;
import com.esq.models.Offer;
import com.esq.models.Route;
import com.example.aeroperu.pojo.OfferPojoGetter;
import com.example.aeroperu.pojo.OfferPojoSetter;
import com.example.aeroperu.service.CabinService;
import com.example.aeroperu.service.OfferService;
import com.example.aeroperu.service.RouteService;

@RestController
@RequestMapping(value = "/offer", produces = "Application/Json")
public class OfferController {

    @Autowired
    OfferService offerService;
    @Autowired
    RouteService routeService;
    @Autowired
    CabinService cabinService;

    @PostMapping("/")
    public Offer createOffer(@Valid @RequestBody OfferPojoSetter pojo) {

	Offer offer = new Offer();
	Route route = routeService.getById(pojo.getRoute_id());
	Cabin cabin = cabinService.getById(pojo.getCabin_id());

	offer.setRoute(route);
	offer.setCabin(cabin);
	offer.setPrice(pojo.getPrice());
	offer.setFrom(pojo.getBeginning());
	offer.setUntil(pojo.getEnd());

	return offerService.newOffer(offer);
    }

    @GetMapping("/")
    public ResponseEntity<List<OfferPojoGetter>> getAllOffers() {
	List<Offer> offers = (List<Offer>) offerService.getAll();

	List<OfferPojoGetter> pojos = new ArrayList<OfferPojoGetter>();

	for (Offer o : offers) {
	    pojos.add(new OfferPojoGetter(o));
	}

	return new ResponseEntity<List<OfferPojoGetter>>(pojos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferPojoGetter> getOfferById(@PathVariable(value = "id") Long airid) {
	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);
	Offer offer = offerService.getById(airid);

	if (offer == null) {
	    return ResponseEntity.notFound().build();
	}

	OfferPojoGetter pojo = new OfferPojoGetter(offer);

	// return ResponseEntity.ok().body(air);
	status = new ResponseEntity<OfferPojoGetter>(pojo, HttpStatus.OK);
	return status;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable(value = "id") Long id,
	    @Valid @RequestBody OfferPojoSetter pojo) {

	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	Offer oldOffer = offerService.getById(id);
	if (oldOffer == null) {
	    return status;
	}

	Cabin cabin = cabinService.getById(pojo.getCabin_id());
	Route route = routeService.getById(pojo.getRoute_id());

	Offer offer = new Offer();
	offer.setId(id);
	offer.setCabin(cabin);
	offer.setFrom(pojo.getBeginning());
	offer.setUntil(pojo.getEnd());
	offer.setPrice(pojo.getPrice());
	offer.setRoute(route);

	Offer updateOffer = offerService.newOffer(offer);

	status = new ResponseEntity<Offer>(updateOffer, HttpStatus.OK);
	return status;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable(value = "id") Long id) {

	ResponseEntity status = new ResponseEntity(HttpStatus.NO_CONTENT);

	Offer offer = offerService.getById(id);
	if (offer == null) {
	    return status;
	}
	offerService.removeOffer(id);
	status = new ResponseEntity(HttpStatus.OK);
	return status;
	// return ResponseEntity.ok().build();

    }
}