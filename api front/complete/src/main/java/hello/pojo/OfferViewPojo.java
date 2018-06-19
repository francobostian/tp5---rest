package hello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OfferViewPojo {
    @JsonProperty
    private CabinPojo cabin;
    @JsonProperty
    private long price;

    public OfferViewPojo(OfferPojoGetter pojo) {
	super();
	this.cabin = pojo.getCabin();
	this.price = pojo.getPrice();
    }
}
