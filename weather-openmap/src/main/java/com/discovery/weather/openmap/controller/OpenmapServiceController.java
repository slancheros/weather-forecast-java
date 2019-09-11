package com.discovery.weather.openmap.controller;



import java.net.URI;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.discovery.weather.openmap.model.Weather;
import com.google.gson.Gson;


@RestController
public class OpenmapServiceController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String  URL = "http://api.openweathermap.org/data/2.5/find?q={location}&units={units}&cnt=1&appid=dc94948d58feeb8dda8f18093f22bd6d";
	
	@RequestMapping(value = "/weekly/{location}/{units}", method = RequestMethod.GET)
	public String getWeeklyForecast(@PathVariable String  location, @PathVariable String units) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("location", location);
		params.put("units", units);
		URI uri = createUri(params);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity <String> requestEntity = new HttpEntity<String>(headers);	
		String response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class).getBody();	
		return parseResponse(response);
	}
	
	private URI createUri( Map<String,String> params) {

		URI uri = UriComponentsBuilder.fromUriString(URL)
		        .buildAndExpand(params)
		        .toUri();
        return uri;
	}
	
	private String parseResponse(String response) {
	   JsonParser parser = JsonParserFactory.getJsonParser();
	   Map<String, Object> map = parser.parseMap(response);
	   List listResponse = (List) map.get("list");
	   
	   Map elements = (LinkedHashMap)listResponse.get(0);
	   Weather weather = new Weather();
	   weather.setCity((String) elements.get("name"));
	   weather.setCountry((String)elements.get("country"));
	   
	   Map measuresMap = (LinkedHashMap)elements.get("main");

	   weather.setTemp(String.valueOf(measuresMap.get("temp")));
	   weather.setMinTemp(String.valueOf(measuresMap.get("temp_min")));
	   weather.setMaxTemp(String.valueOf(measuresMap.get("temp_max")));
	   weather.setPressure(String.valueOf(measuresMap.get("pressure")));
	   weather.setHumidity(String.valueOf(measuresMap.get("humidity")));;

	   List weatherList =(List)elements.get("weather");
	   Map weatherMap = (LinkedHashMap)weatherList.get(0);
	   weather.setType( (String)weatherMap.get("description") );
	   return weatherToJson(weather).toString();
	}
	
	
	  private String weatherToJson(Weather weather) { 
		  Gson gson =new Gson();		   
		  return gson.toJson(weather);
		  }
	 



	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
