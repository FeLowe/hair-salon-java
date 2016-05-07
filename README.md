# Week 3: Java - Database basics independent project

#### Hair Salon Application, 05/06/2016.

#### By Fernanda Lowe

## Description

This application let hair salons create hairstylists's agenda (database) as it stores each client a specific hairstylist has. One hairstylist can have several clients, but a client will only have a hairstylist (as we are still dealing with "one to many" relationship).

Application Webpage: https://infinite-shore-74299.herokuapp.com/

## Setup/Installation Requirements

* CREATE DATABASE hair_salon;
* CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
* CREATE TABLE clients (id serial PRIMARY KEY, name varchar, int stylist_id);
* Create application functionalities (back-end);
* Run all back-end tests;
* Implement User Interface (front-end);
* Run all front-end tests;

## Known Bugs

None;

## Support and contact details

If while using this application you run into any issues or have questions, ideas, concerns, or would like to make a contribution to the code, please contact me at fe_lowe@hotmail.com

## Technologies Used

Java, Spark, Gradle, Velocity, Bootstrap, Heroku, psl

### License

Application can be used under MIT licenses.

Copyright (c) 2016 Fernanda Lowe at Epicodus.
