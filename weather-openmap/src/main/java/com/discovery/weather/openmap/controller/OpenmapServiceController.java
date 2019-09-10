package com.discovery.weather.openmap.controller;



import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;




@RestController
public class OpenmapServiceController {
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String  URL = "http://api.openweathermap.org/data/2.5/find?q={location}&units={units}&cnt=7&appid=dc94948d58feeb8dda8f18093f22bd6d";
	
	@RequestMapping(value = "/weekly/{location}/{units}", method = RequestMethod.GET)
	public String getWeeklyForecast(@PathVariable String  location, @PathVariable String units) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("location", location);
		params.put("units", units);
		URI uri = createUri(params);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity <String> requestEntity = new HttpEntity<String>(headers);	
		String response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class).getBody();	
		return response;
	}
	
	private URI createUri( Map<String,String> params) {

		URI uri = UriComponentsBuilder.fromUriString(URL)
		        .buildAndExpand(params)
		        .toUri();
        return uri;
	}
	

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
