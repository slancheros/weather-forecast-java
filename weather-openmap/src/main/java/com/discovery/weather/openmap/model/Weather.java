package com.discovery.weather.openmap.model;

import java.util.Date;



public class Weather {

	private String city;

	private String country;

	private Date  dateForecast;

	private String type;
	
	private String temp;
	
	private String minTemp;
	
	private String maxTemp;
	
	private String pressure;
	
	private String humidity;
	
	public String getTemp() {
		return temp;
	}


	public void setTemp(String temp) {
		this.temp = temp;
	}


	public String getMinTemp() {
		return minTemp;
	}


	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}


	public String getMaxTemp() {
		return maxTemp;
	}


	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}


	public String getPressure() {
		return pressure;
	}


	public void setPressure(String pressure) {
		this.pressure = pressure;
	}


	public String getHumidity() {
		return humidity;
	}


	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}


	private String units;


	public String getUnits() {
		return units;
	}


	public void setUnits(String units) {
		this.units = units;
	}





	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public Date getDateForecast() {
		return dateForecast;
	}


	public void setDateForecast(Date dateForecast) {
		this.dateForecast = dateForecast;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

}
