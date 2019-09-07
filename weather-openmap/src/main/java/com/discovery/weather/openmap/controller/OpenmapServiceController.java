package com.discovery.weather.openmap.controller;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenmapServiceController {
	
	@RequestMapping(value = "/weekly/{location}/{units}", method = RequestMethod.GET)
	public String getWeeklyForecast(@PathVariable String  location, @PathVariable String units) {
		return "Weekly Forecast " +location+"  in "+units;
	}

}
