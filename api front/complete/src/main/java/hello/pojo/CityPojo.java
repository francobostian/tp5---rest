package hello.pojo;

import com.esq.models.City;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CityPojo {
    @JsonProperty
    private String iata;
    @JsonProperty
    private String name;

    public CityPojo(City city) {
	super();
	this.iata = city.getIataCode();
	this.name = city.getName();
    }
}
