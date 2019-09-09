package com.discovery.gateway.controller;

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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class WeatherController {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value="/weekly/{service}/{location}/{units}", method=RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	public String getWeeklyForecast(@PathVariable String service, @PathVariable String  location, @PathVariable String units) {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity <String> requestEntity = new HttpEntity<String>(headers);	
		Map<String, String> params = new HashMap<String, String>();
		params.put("service", service);
		params.put("location", location);
		params.put("units", units);
		String url = "http://weather-central-service/weekly/{service}/{location}/{units}";
		URI uri = UriComponentsBuilder.fromUriString(url)
		        .buildAndExpand(params)
		        .toUri();
		String response = restTemplate.exchange(uri,  HttpMethod.GET, requestEntity, String.class).getBody();
		return response;
	}
	
	public String  fallbackMethod(@PathVariable String service, @PathVariable String  location, @PathVariable String units){   
        return "Fallback response:: Weather service is not available temporarily";
    }
	
	@Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
