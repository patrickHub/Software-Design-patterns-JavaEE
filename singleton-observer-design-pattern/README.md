# Singleton-Observer-Design-patterns

## Motivation for Singleton Pattern

What is the motivation for the singleton pattern? Well it's about having a single instance of an object in the JVM that is only ever instantiated once within the life cycle of the application. It provides a global point of access to the object, which once created is not destroyed until the application terminates. But why do you need this single object in the first place? The reason is that heavy weight objects, such as connection pools and loggers, are expensive to construct, and therefore you would not want to create them every time you require a connection, or you want to output a message to a log. These objects should be created only once by the singleton, and they're made available to the entire application for the life of the application.

Java EE offers an elegant and easy way to implement the singleton pattern. All we need to do is annotate our class with just one annotation: @Singleton and our class become singleton.

To demonstrate how we can use Singleton pattern, We have create a database connection class which will established connection to mysql
server only once. In order to do that, first we have created a class call [DBConnection](https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/src/main/java/com/patrickhub/dao/DBConnection.jav)a, annoted it with Singleton annotation. then as we want to establsih connection to database only once during the application startup, this class have been annotated with @Startup which will tell to the container to instantiate during the application deploiement. Well we have not done that but we can even inform the container that in case it did not succeed to make connection to the database, it will stop deploiement of the application in order to avoid Runtime Exception during execution. And lastly, we have use PostConstruct annotation in order to decouple the creation of the of the DBConnection object which is our singleton from the establishment of the connection to the database. So the container will first create the singleton object and then create the dababase connection object which is the only DBConnection attribute accessible with its getter.
Now every time we need db connection to make request to the database, the container will simply inject the unique object of this class.

## Motivation for Observer Pattern

The Observer pattern is one of the most widely used and accepted design patterns in modern programming languages, software, and user interface frameworks. Many programming languages use observers within their internal application programming interfaces and Java is no exception. However, Java EE goes further than most and provides an out of the box implementation of the Observer pattern with both synchronous and asynchronous capabilities. Now, the idea behind the observer pattern is that an object that changes its state can inform other objects that a change in state has occurred. In the language of the design pattern, the object that changes its state is called the subject, whereas those objects that receive notification of the change of state are called the observers.

The Java EE implementation relies on the Observes annotation. It is used to mark the observers that will receive the message sent by the subject. The subject uses the Event class to create and fire events that the observers listen for without knowing who is listinning.

To demonstrate how can use Observer pattern, we have create a simple REST-API application with JAX-RS. First we have created our resource class called (CustomerResource)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/src/main/java/com/patrickhub/rest/CustomerRessource.java. That class will be our Subject. Every time it receive a http request it will fire an Even instance of type Customer. Then we have created two DAO class which will be our Observers. the first called (CustomerDao)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/src/main/java/com/patrickhub/dao/CustomerDao.java which will make CRUD operations on Customer table in database. The second is call (AuthentiationDao)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/src/main/java/com/patrickhub/dao/AuthenticationDao.java which will CRUD operations on user credentials. Now how does this work? Well when a POST request is made to created customer, the createCustomer method of CustomerResource class will fire an Event of type Customer with the customer payload sent by the request. The createUserCredentials method of AuthenticationDao class will catch the event save the user credentials from the payload send with the even and then fire another even of type Customer with the same payload. That even is catched by the saveCustomer method of CustomerDao class to save customer information to the database and create the customer it will send a welcome email to the customer. Secondly when the PUT request is made the same process is done but the even fire by the updateCustomer method of CustomerResource class is catch both by the updateUserCredentials method of AuthenticationDao class and updateCustomer method of the CustomerDao class. The same process is also made for the DELETE request but with an even of type Integer as we just need the user Id to delete user and customer.

We can see that we have many evens of type customer that are fired during the POST and the UPDATE requests. How can the observers know which even of type customer to catch. Thanks to a feature called Qualifier. A Qualifier allows the container to disambiguate between instances injected into the class, now with the use of qualifiers, I could inject differents events instances of type customer. Well then I have created our Qualifier which an annotated class called (CustomerEvent)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/src/main/java/com/patrickhub/events/CustomerEvent.java and annotated it with @Qualifier annotation. This class has a type attribute that can be ADD, UPDATE, USERADD or USERDELETE Enum.

I also use the feature of Priority to specify in which order the observers should be invoked. This is a new feature in Java EE 8. The lowest value priorities are called first and if no priority is specified, then the default middle value is assumed, which is the application constant from the priority class plus 500.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

To run this application you need to have theses applications install on your operating system regardless on which oparating system your are using:

- JDK 8
  to see which JDK version your are using simply open a terminal an type this command:

```
java -version
```

- (Docker Desktop)https://www.docker.com/products/docker-desktop 18.09.1 or higher
  To check your docker version type this command on your terminal

```
 docker version
```

Windows 10 operating system should have Git Bash install or a Bash Shell Command-line tool install in order to run the Bash script file.

### Running the Application

To run the application first make sure that port 8080 is not use in your operating system.
Then clone this repository and run the app with the following commands:

```
git clone https://github.com/patrickHub/Software-Design-patterns-JavaEE.git
cd Software-Design-patterns-JavaEE
cd singleton-observer-design-pattern
bash buildAndRun.sh
```

And you can start make request to localhost:8080/singleton-observer-design-pattern/api

To simplefy every thing we have use:

- (Dockerfile)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/Dockerfile which pull wildfly:15.0.0 from jboss/wildfly:15.0.0.Final then copy the
  _singleton-observer-design-pattern.war_ file to /opt/jboss/wildfly/standalone/deployments/
- (docker-compose.yml)https://github.com/patrickHub/Software-Design-patterns-JavaEE/blob/master/singleton-observer-design-pattern/docker-compose.yml which will create two services. One for build of the previous _Dockerfile_ and the other for mysql:5.7 image. As the _singleton-observer-design-pattern.dev_ container should make database query to the database _singleton-observer-design-pattern-mysql.dev_ container, we have used the feature of Network. So the two containers are sharing the same network _singleton-observer-design-pattern-network_ and this is what will enable those containers to speach each other.

## Authors

- **(PatrickHub)[https://github.com/patrickHub]**
 
