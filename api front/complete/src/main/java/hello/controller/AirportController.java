package hello.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import hello.pojo.AirportPojo;
import hello.pojo.AirportViewPojo;

@Controller
public class AirportController {

    // implements CommandLineRunner
    // @RequestMapping("/greetings/{id}")
    // public String greeting(@RequestParam(name = "name", defaultValue =
    // "default") String palabra, Model model,
    // @PathVariable String id) {
    //
    // RestTemplate restTemplate = new RestTemplate();
    // AirportPojo AirportPojo =
    // restTemplate.getForObject("http://localhost:8888/AirportPojo/" + id,
    // AirportPojo.class);
    // System.out.println(AirportPojo.toString());
    // model.addAttribute("name", AirportPojo.getCity().toString());
    // return "greetings";
    // }

    @RequestMapping(value = "/port/", method = RequestMethod.GET)
    public ResponseEntity<List<AirportViewPojo>> getAirports() {
	RestTemplate restTemplate = new RestTemplate();
	AirportPojo[] basePojos = restTemplate.getForObject("http://localhost:8888/airport/", AirportPojo[].class);
	List<AirportPojo> list = new ArrayList<AirportPojo>(Arrays.asList(basePojos));
	List<AirportViewPojo> pojos = new ArrayList<AirportViewPojo>();

	for (AirportPojo basePojo : list) {
	    pojos.add(new AirportViewPojo(basePojo));
	}

	return ResponseEntity.ok().body(pojos);
    }

}
