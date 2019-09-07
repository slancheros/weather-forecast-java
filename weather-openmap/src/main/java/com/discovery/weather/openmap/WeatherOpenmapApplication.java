package com.discovery.weather.openmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class WeatherOpenmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherOpenmapApplication.class, args);
	}

}
