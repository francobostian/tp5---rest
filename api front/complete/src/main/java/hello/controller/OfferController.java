package hello.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import hello.pojo.OfferPojoGetter;
import hello.pojo.OfferViewPojo;

@Controller
public class OfferController {

    @RequestMapping(value = "/offer/{iata}/{iata2}/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<OfferViewPojo>> getOffers(@PathVariable("iata") String iata,
	    @PathVariable("iata2") String iata2, @PathVariable("date") String date) throws ParseException {
	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);

	RestTemplate restTemplate = new RestTemplate();
	OfferPojoGetter[] offer = restTemplate.getForObject("http://localhost:8888/offer/", OfferPojoGetter[].class);
	List<OfferPojoGetter> list = new ArrayList<OfferPojoGetter>(Arrays.asList(offer));

	List<OfferViewPojo> pojos = new ArrayList<OfferViewPojo>();

	for (int j = 0; j < list.size(); j++) {
	    if (list.get(j).getRoute().getOrigin().getIata().equals(iata)
		    && list.get(j).getRoute().getDestination().getIata().equals(iata2)
		    && CompareDate(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(j).getFrom()), d)
		    && CompareDate(d, new SimpleDateFormat("dd-MM-yyyy").parse(list.get(j).getUntil()))) {

		pojos.add(new OfferViewPojo(list.get(j)));
	    }
	}
	if (offer == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(pojos);
    }

    @RequestMapping(value = "/offer/{iata}/{iata2}/{date}/{cabinId}", method = RequestMethod.GET)
    public ResponseEntity<List<OfferViewPojo>> getOffersFilterCabin(@PathVariable("iata") String iata,
	    @PathVariable("iata2") String iata2, @PathVariable("date") String date,
	    @PathVariable("cabinId") long cabinId) throws ParseException {
	Date d = new SimpleDateFormat("dd-MM-yyyy").parse(date);

	RestTemplate restTemplate = new RestTemplate();
	OfferPojoGetter[] offer = restTemplate.getForObject("http://localhost:8888/offer/", OfferPojoGetter[].class);
	List<OfferPojoGetter> list = new ArrayList<OfferPojoGetter>(Arrays.asList(offer));

	List<OfferViewPojo> pojos = new ArrayList<OfferViewPojo>();

	for (int j = 0; j < list.size(); j++) {
	    if (list.get(j).getRoute().getOrigin().getIata().equals(iata)
		    && list.get(j).getRoute().getDestination().getIata().equals(iata2)
		    && CompareDate(new SimpleDateFormat("dd-MM-yyyy").parse(list.get(j).getFrom()), d)
		    && CompareDate(d, new SimpleDateFormat("dd-MM-yyyy").parse(list.get(j).getUntil()))
		    && list.get(j).getCabin().getId() == cabinId) {

		pojos.add(new OfferViewPojo(list.get(j)));
	    }
	}
	if (offer == null) {
	    return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok().body(pojos);
    }

    private boolean CompareDate(Date before, Date after) {
	return before.before(after) || before.equals(after);
    }
}
