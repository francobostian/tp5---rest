package hello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OfferPojoSetter {

    @JsonProperty
    private long id;
    @JsonProperty
    private long route_id;
    @JsonProperty
    private long cabin_id;
    @JsonProperty
    private long price;
    @JsonProperty
    private String desde;
    @JsonProperty
    private String hasta;

    public OfferPojoSetter(long id, long route_id, long cabin_id, long price) {
	super();
	this.id = id;
	this.route_id = route_id;
	this.cabin_id = cabin_id;
	this.price = price;
    }

    public OfferPojoSetter(long route_id, long cabin_id, long price, String desde, String hasta) {
	super();
	// this.id = id;
	this.route_id = route_id;
	this.cabin_id = cabin_id;
	this.price = price;
	this.desde = desde;
	this.hasta = hasta;
    }

}
