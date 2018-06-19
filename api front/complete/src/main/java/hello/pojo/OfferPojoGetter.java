package hello.pojo;

import com.esq.models.Offer;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OfferPojoGetter {

    @JsonProperty
    private RoutePojoGetter route;
    @JsonProperty
    private CabinPojo cabin;
    @JsonProperty
    private long price;
    @JsonProperty
    private String from;
    @JsonProperty
    private String until;

    public OfferPojoGetter(Offer offer) {
	super();
	this.route = new RoutePojoGetter(offer.getRoute());
	this.cabin = new CabinPojo(offer.getCabin());
	this.price = offer.getPrice();
	this.from = offer.getFrom();
	this.until = offer.getUntil();
    }
}
