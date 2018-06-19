package hello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportViewPojo {

    @JsonProperty
    private String name;
    @JsonProperty
    private String iataCode;

    public AirportViewPojo(AirportPojo pojo) {
	super();
	this.iataCode = pojo.getIata();
	this.name = pojo.getCity().getName() + " - " + pojo.getName() + " - " + pojo.getCountry().getName();
    }

}
