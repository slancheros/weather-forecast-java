package com.discovery.weather.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WeatherCentralApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherCentralApplication.class, args);
	}

}
