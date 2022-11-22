# ALTEN BOOKING HOTEL
### Overview

The following repository contains the booking microservice created based on the rules specified in the technical document.

Developer: Rolando Paredes Alzamora


#### Table of contents
* [Technologies](#technologies)
* [Architecture](#architecture)
* [Structure](#structure)
* [Principles](#principles)
* [Patterns](#patterns)
* [Database](#database)
* [Logging](#logging)
* [Infrastructure](#infrastructure)
* [Security](#security)
* [CleanCode](#cleancode)
* [UnitTesting](#unittesting)
* [Swagger](#swagger)
* [Repository](#repository)


#### Technologies
The technologies used in this project are the following:

- JDK 17
- SPRING BOOT 2.7.5
- JPA 
- H2

#### Architecture

For this project I used a clean architecture using Hexagonal Architecture and DDD

Where I separated the corresponding objects by layers

Since its hexagonal architecture I separated into 3 main layers:

```
application, domain, infrastructure

```

Where "application" interacts with the client

Where "domain" handle also business logic 

Where "infrastructure" deals with the database connection

Also separated in ports (interfaces) and adapters (interfaces implementation)

#### Structure

The project structure is related to its architecture 

```
com.alten.hotel.booking
                       .application
                       .domain
                       .infrastructure 

```
And where ports and adapters differentiates where port plays as an interface and the adapter as a consumer 

```
ports: (Iservice, IRespository)
adapters: (Service, Repository)

```

#### Principles

To design the tables for the database I based on ACID principles

```
atomicity, consistency, isolation, durability

```

And also in the coding part I based on SOLID principles with the use of interfaces and corresponding injections 


#### Patterns

For this project I used the following design patterns:
- Singlenton 
- Facade

The first one in persistence via Spring data since by default register their beans as singlenton scope.

The second where logic is in service layer, the controller does not know what it does to get a response, it just call the corresponding method

#### Database

For database I used H2 in memory database

Some records are added while deploying the application, just for testing purpose

To access the database in the UI, it can be access by the following link:
http://localhost:8080/h2-ui/

- Driver: jdbc:h2:mem:booking
- User: sa
- Password: (empty)


#### Logging

For logging I used SLF4J
To enable logging in the project logback.xml was needed and also some values in properties file

That generates a "logs" folder with a log file call "app.log" where logging are written in


#### Infrastructure

Since it was mentioned in the technical document about no downtime

It's suggested to have multiple instances of the microservice

Can use docker to create a docker image and with kubernetes technology create multiple replicas of a pod containing this docker image.

A kubernetes service type loadbalancer might do the work between the instances created for this microservice.


#### Security

As it was said in the technical document, it was no considered that the apis have security.

It may be recommended to use jwt in headers and also if part of the functionality a user has to be authenticated then must send the accesstoken in every each request to the backend to validate authenticity


#### CleanCode

To be sure of clean code I used sonarlint plugin in my IDE.

That way I was able to correct the warnings the plugin showed

#### UnitTesting

For Unit Testing I used Mockito library with jupiter to handle the corresponding testing.

#### Swagger

I used a swagger library and defined in the code the controllers method and input parameters


#### Repository

To access the code in github it can be clone from this repository:

- https://github.com/RolandoParedes/booking





