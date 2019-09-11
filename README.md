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

This microservice aggregates the weather information from other microservices, so it makes use of the discovery server to call other microservices and obtain the standard response. The goal is to have many services integrated using a standard message and visualized in a GUI in which the user can select the service ( openmap, yahoo, forecast.io, etc).

## Openmap Weather Service
This microservice consumes the current weather forecast information from OpenWeather service (http://localhost:8010/hystrix). It receives a json formatted information for a given city(using a city name is ok) in given units (metric /imperial). Using metric units will return temperature in °C (Celsius), whereas imperial units will return temperature in °F (Fahrenheit).

# Installation

To install:

1. Clone this repository
2. Import as Existing Maven projects, each one of the components into Spring Tool Suite or Intellij IDEA ( with Spring plugin)
3. Run as SpringBoot app, suggested order is the following:
     1. Discovery server
     2. Gateway server
     3. Weather central
     5. Openmap weather service.
     

Pre- installation requirements:

1. JDK 1.8 and its JRE.
2. Spring Tool Suite/IntelliJIdea 






