package com.discovery.weather.openmap.controller;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.discovery.weather.openmap.model.Weather;
import com.google.gson.Gson;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(OpenmapServiceController.class)
public class OpenmapServiceControllerTest {



   @Mock
   private RestTemplate restTemplate;

   @InjectMocks
   private OpenmapServiceController controller;
   
    
	@Test
	public void givenForecast_testGetWeeklyForecast() throws URISyntaxException {
	   Weather weather = new Weather();
	   weather.setCity("Bogota");
	   weather.setHumidity("76");
	   weather.setPressure("1029");
	   weather.setTemp("13");
	   weather.setMinTemp("13");
	   weather.setMaxTemp("13");
	   weather.setType("scattered clouds");
	   
	   Gson gson =new Gson();		   
	   String weatherJson = gson.toJson(weather).toString();
	   
	   URI uri = new URI("http://api.openweathermap.org/data/2.5/find?q=Bogota&units=metric&cnt=1&appid=dc94948d58feeb8dda8f18093f22bd6d");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity <String> requestEntity = new HttpEntity<String>(headers);
		
		
		
	   Mockito
	       .when(restTemplate.exchange(uri,HttpMethod.GET, requestEntity, String.class).getBody() )
	       .thenReturn(("{\"message\":\"accurate\",\"cod\":\"200\",\"count\":1,\"list\":[{\"id\":3688689,\"name\":\"Bogota\",\"coord\":{\"lat\":4.5981,\"lon\":-74.0762},\"main\":{\"temp\":13,\"pressure\":1029,\"humidity\":76,\"temp_min\":13,\"temp_max\":13},\"dt\":1568174968,\"wind\":{\"speed\":2.1,\"deg\":80},\"sys\":{\"country\":\"CO\"},\"rain\":null,\"snow\":null,\"clouds\":{\"all\":40},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}]}]}"));
	   
       String response = controller.getWeeklyForecast("Bogota", "metric");
       assertEquals("Response equals weather expected", weatherJson, response);
	
	}

}
