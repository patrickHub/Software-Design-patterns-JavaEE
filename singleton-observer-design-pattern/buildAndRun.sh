#!/bin/sh
mvn clean package && docker build -t com.patrickhub/singleton-observer-design-pattern .
docker rm -f singleton-observer-design-pattern || true && docker run -d -p 8080:8080 -p 4848:4848 --name singleton-observer-design-pattern com.patrickhub/singleton-observer-design-pattern 
