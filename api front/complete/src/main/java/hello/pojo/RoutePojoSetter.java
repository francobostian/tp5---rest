package hello.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoutePojoSetter {

    @JsonProperty
    private long id;
    @JsonProperty
    private String origin;
    @JsonProperty
    private String destino;
    @JsonProperty
    private int distancia;

    public RoutePojoSetter(long id, String origin, String destino, int distancia) {
	super();
	this.id = id;
	this.origin = origin;
	this.destino = destino;
	this.distancia = distancia;
    }

}
