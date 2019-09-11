# Weather Central Project

This component will aggregate the  daily weather forecast from some of the open Weather APIs. 
The user is able to select the service, location and units through the UI.

# Implementation
The project uses microservices to retrieve information from each weather service. Microservices are coded in Java with Spring stack and make part of an Eureka network, which allows them to be connected and discovered dinamically.

# Components
The following is the hierarchy of the components in the microservices architecture proposed. Each component plays a role in the architecture.

## Discovery server
This microservice contains the Eureka discovery service which allows other services to discover and call each other services, as part of being a reactive system.

The Eureka admin page will be running in http://localhost:8761

## Gateway
This microservice reprsents a gateway and monitoring component for weather microservices. The point of having a gateway is to centralize interaction with the system.

Monitoring of the system would be accessed through this location http://localhost:8010/hystrix

Additonally this component contains a circuit breaker strategy, given that when a service requested is not available, it provides a customized Fallback response.

## Weather Central

This microservice aggregates the weather information from other microservices, so it makes use of the discovery server to call other microservices and obtain the standard response.

## Openmap Weather Service
This microservice consumes the current weather forecast information from OpenWeather service (http://localhost:8010/hystrix). It receives a json formatted information for a given city(using a city name is ok) in given units (metric /imperial). Using metric units will return temperature in °C (Celsius), whereas imperial units will return temperature in °F (Fahrenheit).






