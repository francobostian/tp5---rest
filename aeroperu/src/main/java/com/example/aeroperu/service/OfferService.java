package com.example.aeroperu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esq.models.Offer;
import com.example.aeroperu.repo.OfferRepo;

@Service
public class OfferService {

    @Autowired
    private OfferRepo offer;

    public List<Offer> getAll() {
	return this.offer.findAll();
    }

    public Offer getById(Long id) {
	Offer offer = null;
	Optional<Offer> offerOptional = this.offer.findById(id);
	if (offerOptional.isPresent()) {
	    offer = offerOptional.get();
	}
	return offer;
    }

    public Offer newOffer(Offer value) {
	if (value != null) {
	    this.offer.save(value);
	}
	return value;
    }

    public void removeOffer(Long id) {
	this.offer.deleteById(id);
    }
}
