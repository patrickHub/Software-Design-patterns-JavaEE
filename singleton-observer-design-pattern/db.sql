CREATE DATABASE IF NOT EXISTS singletonObserverDesignPattersdb;
USE singletonObserverDesignPattersdb;


CREATE TABLE users(
	userID INT AUTO_INCREMENT,
	username VARCHAR (10) UNIQUE NOT NULL ,
    password VARCHAR (10) NOT NULL,
    
    CONSTRAINT users_PK PRIMARY KEY (userID)
);

CREATE TABLE customers(
	customerID INT AUTO_INCREMENT,
    customerFirstName VARCHAR(30) NOT NULL,
    customerLastName VARCHAR(30) NOT NULL,
	customerEmail VARCHAR(30) NOT NULL,
    customerPhone VARCHAR(10) NOT NULL,
    customerBirthdate DATE NOT NULL,
    
    CONSTRAINT Customer_PK PRIMARY KEY (customerID)
    
);