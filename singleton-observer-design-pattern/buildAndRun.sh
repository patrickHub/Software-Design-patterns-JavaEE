#!/bin/sh
mvn clean package && docker rm -f singleton-observer-design-pattern.dev ||
    docker rm -f singleton-observer-design-pattern-mysql.dev || 
    docker rmi patrickhub/singleton-observer-design-pattern:1.0-SNAPSHOT ||
    true && docker-compose up