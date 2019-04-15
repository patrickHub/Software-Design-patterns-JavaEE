use mysql;
CREATE USER 'admin'@'%' IDENTIFIED BY 'admin';
GRANT ALL ON *.* TO 'admin'@'%';

CREATE DATABASE
IF NOT EXISTS singletonObserverDesignPattersdb;
USE singletonObserverDesignPattersdb;


CREATE TABLE users
(
    userID INT
    AUTO_INCREMENT,
	username VARCHAR(10) NOT NULL ,
    password VARCHAR(10) NOT NULL,
    
    CONSTRAINT users_PK PRIMARY KEY
    (userID)
);

    CREATE TABLE customers
    (
        customerID INT
        AUTO_INCREMENT,
    customerFirstName VARCHAR
        (30) NOT NULL,
    customerLastName VARCHAR
        (30) NOT NULL,
	customerEmail VARCHAR
        (30) NOT NULL,
    customerPhone VARCHAR
        (10) NOT NULL,
    customerBirthdate DATE NOT NULL,
    userID INT,
    
    CONSTRAINT Customer_PK PRIMARY KEY
        (customerID),
    CONSTRAINT Customer_User_FK FOREIGN KEY
        (userID) REFERENCES users
        (userID)
    
);