package hello.pojo;

import com.esq.models.Cabin;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CabinPojo {
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;

    public CabinPojo(Cabin cabin) {
	super();
	this.id = cabin.getId();
	this.name = cabin.getName();
    }
}
